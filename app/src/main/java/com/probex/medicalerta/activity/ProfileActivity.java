package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.probex.medicalerta.R;
import com.probex.medicalerta.adapter.Notificacao;
import com.probex.medicalerta.adapter.Usuario;
import com.probex.medicalerta.database.BancoDadosMed;
import com.probex.medicalerta.helper.ImageSaver;
import com.probex.medicalerta.model.AppUtils;

import java.util.Date;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private TextView  profileName, profileAge;
    private ImageView profilePicView;
    private BancoDadosMed bancoDadosMed;
    private List<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePicView = findViewById(R.id.profilePicView);
        profileAge = findViewById(R.id.profileAge);
        profileName = findViewById(R.id.profileName);

        bancoDadosMed = new BancoDadosMed(this);

        usuarios = bancoDadosMed.listaTodosUsuarios();
        bancoDadosMed.close();

        if(usuarios.size() != 0) {
            Usuario usuario =  usuarios.get(0);
            int ft;

            try {
                //For some reason it does not work out if i use a string so i had to convert it to int
                ft = Integer.parseInt(usuario.getFoto());
            }catch (Exception e) {
                ft = 1;
            }

           if(ft != 0) {
               Bitmap bitmap = new ImageSaver(this).
                       setFileName(usuario.getFoto()).
                       setDirectoryName("images").
                       load();
               profilePicView.setImageBitmap(bitmap);
           }

            String[] name = usuario.getNome().split(" ");
            profileName.setText(name[0]);

            int age = AppUtils.getAge(new Date(usuario.getDataNascimento()));
            profileAge.setText(age + " anos");

        }
    }

    public void openHistoryListActivity(View view) {
        startActivity(new Intent(this, HistoryActivity.class));
    }

    public void openNotificationActivity(View view) {
        startActivity(new Intent(this, NotificationsActivity.class));
    }

    public void openCreateUserActivity(View view) {
        startActivity(new Intent(this, CreateUserActivity.class));
    }

    public void voltar(View view) {
        finish();
        overridePendingTransition(0, R.anim.mover_direita);
    }
}