package com.probex.medicalerta.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.datepicker.MaterialTextInputPicker;
import com.google.android.material.textfield.TextInputLayout;
import com.probex.medicalerta.R;
import com.probex.medicalerta.adapter.Usuario;
import com.probex.medicalerta.database.BancoDadosMed;
import com.probex.medicalerta.helper.ImageSaver;
import com.probex.medicalerta.helper.Permission;

public class CreateUserActivity extends AppCompatActivity {
    private Button materialTextInputPicker, createUserButton;
    private ImageView profilePicView;
    private TextInputLayout userNameTextInput;

    private BancoDadosMed bancoDadosMed;

    private String userName;
    private long selectedDate;
    private String imageName;

    private Bitmap image = null;

    private String[] necessaryPermissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private static final int SELECTION_CAMERA  = 100;
    private static final int SELECTION_GALLERY = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        final Context context = this;

        bancoDadosMed = new BancoDadosMed(this);

        Permission.validatePermissions(necessaryPermissions, this,1);

        profilePicView = findViewById(R.id.profilePicView);
        userNameTextInput = findViewById(R.id.userNameTextInput);
        materialTextInputPicker = findViewById(R.id.userDatePicker);
        createUserButton = findViewById(R.id.createUserButton);

        profilePicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if ( i.resolveActivity(getPackageManager()) != null ){
                    startActivityForResult(i, SELECTION_GALLERY );
                }

            }
        });

        userNameTextInput.getEditText().addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                userName = s.toString();
                //get the String from CharSequence with s.toString() and process it to validation

            }
        });


        //Material date picker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Data de nascimento");
        final MaterialDatePicker materialDatePicker = builder.build();


        materialTextInputPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                materialTextInputPicker.setText(materialDatePicker.getHeaderText());
                selectedDate = selection;
            }
        });

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageName = "myImage.jpeg";
                if(image != null) {
                    new ImageSaver(context).
                            setFileName(imageName).
                            setDirectoryName("images").
                            save(image);
                } else {
                    imageName = "0";
                }

                bancoDadosMed.addUsuario(new Usuario(userName, selectedDate, imageName));
                bancoDadosMed.close();

                startActivity(new Intent(context, MainActivity.class));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( resultCode == RESULT_OK ){

            try {
                switch ( requestCode ){
                    case SELECTION_CAMERA:
                        image = (Bitmap) data.getExtras().get("data");
                        break;
                    case SELECTION_GALLERY:
                        Uri storedImagePath = data.getData();
                        image = MediaStore.Images.Media.getBitmap(getContentResolver(), storedImagePath );
                        break;
                }

                if ( image != null ){

                    profilePicView.setImageBitmap(image);

                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for ( int permissionResult : grantResults ){
            if ( permissionResult == PackageManager.PERMISSION_DENIED ){
                validatePermissionAlert();
            }
        }

    }

    private void validatePermissionAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

}