package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

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
import com.probex.medicalerta.services.AlarmService;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView notificationAmount, profileNameMain, profileAgeMain;
    private ImageView mainProfilePic;
    private BancoDadosMed bancoDadosMed;
    private List<Notificacao> notificacoes;
    private List<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(getBaseContext(), AlarmService.class));

        mainProfilePic = findViewById(R.id.mainProfilePic);
        profileAgeMain = findViewById(R.id.profileAgeMain);
        profileNameMain = findViewById(R.id.profileNameMain);

        bancoDadosMed = new BancoDadosMed(this);

        usuarios = bancoDadosMed.listaTodosUsuarios();

        if(usuarios.size() != 0) {
           Usuario usuario =  usuarios.get(0);
            int ft;

            try {
                //For some reason it does not work out if i use a string so i had to convert it to int
                ft = Integer.parseInt(usuario.getFoto());
            }catch (Exception e) {
                ft = 1;
            }

            if (ft != 0) {
                Bitmap bitmap = new ImageSaver(this).
                        setFileName(usuario.getFoto()).
                        setDirectoryName("images").
                        load();
                mainProfilePic.setImageBitmap(bitmap);
            }

            String[] name = usuario.getNome().split(" ");
            profileNameMain.setText(name[0]);

            int age = AppUtils.getAge(new Date(usuario.getDataNascimento()));
            profileAgeMain.setText(age + " anos");

        }

        notificacoes = bancoDadosMed.listaTodasNotificacao();
        System.out.println("Notification size: " + notificacoes.size());

        notificationAmount = findViewById(R.id.notificationAmountIcon);

        if(bancoDadosMed.listaTodasNotificacao().size() != 0) {
            notificationAmount.setVisibility(View.VISIBLE);
            notificationAmount.setText(Integer.toString(notificacoes.size()));
        }

        bancoDadosMed.close();
    }

    public void openAddMedication1(View view) {
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.mover_esquerda ,R.anim.fade_out );
        ActivityCompat.startActivity(MainActivity.this, new Intent(MainActivity.this, AddMedication1.class), activityOptionsCompat.toBundle());
    }
    public void openListaMedicamento2(View view) {
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.mover_esquerda ,R.anim.fade_out );
        ActivityCompat.startActivity(MainActivity.this, new Intent(MainActivity.this, ListaMedicamento1.class), activityOptionsCompat.toBundle());
    }
    public void openAlarmListActivity(View view) {
        startActivity(new Intent(this, AlarmListActivity.class));
    }
    public void openHistoryListActivity(View view) {
        startActivity(new Intent(this, HistoryActivity.class));
    }
    public void openProfileActivity(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }
}