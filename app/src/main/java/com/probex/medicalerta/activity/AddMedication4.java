package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.probex.medicalerta.R;
import com.probex.medicalerta.adapter.Alarme;
import com.probex.medicalerta.adapter.Medicamento;
import com.probex.medicalerta.database.BancoDadosMed;
import com.probex.medicalerta.database.BancoListaMed;

public class AddMedication4 extends AppCompatActivity {

    private TextView produto, concentracao, intervalo, qtdDias;
    private Button greenBtn, redBtn;
    private BancoListaMed bancoListaMed;
    private Medicamento med;
    private Alarme alarme;
    private String resultadoDias, resultadoAlarme;
    private static final int SUCCESS = 1, ERROR = -1;
    BancoDadosMed db = new BancoDadosMed(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication4);


        alarme = getIntent().getExtras().getParcelable("alarme");
        String intentMed = getIntent().getExtras().getString("medicamento");


        bancoListaMed = new BancoListaMed(this);
        bancoListaMed.selecionarMedicamento(Integer.valueOf(intentMed));
        med = bancoListaMed.selecionarMedicamento(Integer.valueOf(intentMed));

        greenBtn = (Button) findViewById(R.id.green_btn);
        redBtn = (Button) findViewById(R.id.red_btn);

        produto = (TextView) findViewById(R.id.produto);
        concentracao = (TextView) findViewById(R.id.concentracao);
        intervalo = (TextView) findViewById(R.id.intervalo);
        qtdDias = (TextView) findViewById(R.id.qtdDias);


        //Contar quantidade de dias
        if (alarme.getData_final() == 0) {
            resultadoDias = "Tempo indeterminado";
        } else {
            long ini = alarme.getData_inicial();
            long fin = alarme.getData_final();
            long res = (fin - ini) / 86400000;
            resultadoDias = (res == 1) ? String.valueOf(res) + " dia" : String.valueOf(res) + " dias";
        }

        //Quantidade de vezes
        if (alarme.getIntervalo() == 1) {
            resultadoAlarme = "Uma vez ao dia";
        } else {
            resultadoAlarme = String.valueOf(alarme.getIntervalo() + " vezes ao dia");
        }

        produto.setText(String.valueOf(med.getProduto()));
        concentracao.setText(String.valueOf(med.getConcentracao()));
        intervalo.setText(resultadoAlarme);
        qtdDias.setText(resultadoDias);

    }

    private void showToast(int type, String message) {
        ViewGroup view = findViewById(R.id.container_toast);
        View v = getLayoutInflater().inflate(R.layout.custom_toast, view);

        switch (type) {
            case SUCCESS:
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_sucess));
                break;
            case ERROR:
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_error));
                break;

        }

        TextView txtMessage = v.findViewById(R.id.txt_message);
        txtMessage.setText(message);

        Toast toast = new Toast(this);
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void voltar(View view) {
        finish();
        overridePendingTransition(0, R.anim.mover_direita);
    }

    public void onBackPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void continuar(View view) {

        try {
            if (db.selecionarMedicamento(med.getId_med()) != null) {
                showToast(ERROR, "Medicamento já foi cadastrado");
            }
        } catch (Exception e) {
            db.addMedicamento(med);
            db.addAlarme(alarme);

            showToast(SUCCESS, "Medicamento cadastrado");
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}