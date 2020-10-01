package com.probex.medicalerta.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.probex.medicalerta.R;
import com.probex.medicalerta.database.BancoListaMed;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class AddMedication2 extends AppCompatActivity {

    private PostAdapter postAdapter;
    private BancoListaMed bancoListaMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication2);

        //Receber valores
        String value = getIntent().getStringExtra("key");


        //iniciando Lista
        initBancoListaMed();


        RecyclerView rv = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);

        List<Medicamento> posts = bancoListaMed.allMedicamento(value);

        postAdapter = new PostAdapter(posts);
        rv.setAdapter(postAdapter);

    }

    private void initBancoListaMed() {
        bancoListaMed = new BancoListaMed(this);

        File database = getApplicationContext().getDatabasePath(BancoListaMed.NOMEDB);
        if (database.exists() == false) {
            bancoListaMed.getReadableDatabase();
            //RETIRAR
            if (copiaBanco(this)) {
                alert("Banco copiado com sucesso");
            } else {
                alert("Erro ao copiar");
            }
        }
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    private boolean copiaBanco(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(BancoListaMed.NOMEDB);
            String outFile = BancoListaMed.LOCALDB + BancoListaMed.NOMEDB;
            OutputStream outputStream = new FileOutputStream(outFile);
            byte[] buff = new byte[1024];
            int legth = 0;

            while ((legth = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, legth);
            }
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolderPost> {

        private List<Medicamento> dados;

        public PostAdapter(List<Medicamento> dados) {
            this.dados = dados;
        }

        @NonNull
        @Override
        public PostAdapter.ViewHolderPost onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.add_feed, parent, false);
            ViewHolderPost holderPost = new ViewHolderPost(view);

            return holderPost;
        }

        @Override
        public void onBindViewHolder(@NonNull PostAdapter.ViewHolderPost holder, int position) {

            if ((dados != null) && (dados.size() > 0)) {

                Medicamento post = dados.get(position);

                holder.produto.setText(post.getProduto());
                holder.substancia.setText(post.getSubstancia());
                holder.concentracao.setText(post.getConcentracao());
                holder.subclasse.setText(post.getSubclasse());
                holder.idV = post.getId_med();
            }
        }

        @Override
        public int getItemCount() {
            return dados.size();
        }


        public class ViewHolderPost extends RecyclerView.ViewHolder {

            public final TextView produto;
            public final TextView substancia;
            public final TextView concentracao;
            public final TextView subclasse;
            public Integer idV;
            public final Button btn;


            public ViewHolderPost(@NonNull final View itemView) {
                super(itemView);

                produto = (TextView) itemView.findViewById(R.id.produto);
                substancia = (TextView) itemView.findViewById(R.id.substancia);
                concentracao = (TextView) itemView.findViewById(R.id.concentracao);
                subclasse = (TextView) itemView.findViewById(R.id.subclasse);
                btn = (Button) itemView.findViewById(R.id.green_btn);


                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click();
                    }
                });
            }

            public void click(){
                //alert("teste");
                Intent intent = new Intent(AddMedication2.this, AddMedication3.class);
                intent.putExtra("key", idV.toString());
                startActivity(intent);
            }


        }

    }
}