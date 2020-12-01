package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.probex.medicalerta.R;
import com.probex.medicalerta.adapter.Alarme;
import com.probex.medicalerta.adapter.Medicamento;
import com.probex.medicalerta.adapter.Produto;
import com.probex.medicalerta.database.BancoDadosMed;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ListaMedicamento1 extends AppCompatActivity {

    BancoDadosMed db = new BancoDadosMed(this);
    Drawable drawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicamento1);
        List<Medicamento> listaDeMedicamento = new ArrayList<>();

        listaDeMedicamento = db.listaTodosMedicamentos();
        ExpandableListView elvCompra = (ExpandableListView) findViewById(R.id.elvCompra);
        List<String> lstGrupos = new ArrayList<>();
        List<Produto> lstMedicamento;
        HashMap<String, List<Produto>> lstItensGrupo = new HashMap<>();


        for (int i = 0; i < listaDeMedicamento.size(); i++) {
            Alarme alarme = db.selecionarAlarme(listaDeMedicamento.get(i).getId_med());
            lstMedicamento = new ArrayList<>();

            //Diferenciar dos outros listaDeMedicamento.get(i).getId_med()
            lstGrupos.add(listaDeMedicamento.get(i).getProduto() + "!" + listaDeMedicamento.get(i).getIndicacao()  + "!" + listaDeMedicamento.get(i).getId_med());


            String qtdDias;
            long l;
            if (alarme.getData_final() != 0) {
                l = (alarme.getData_final() - alarme.getData_inicial()) / 86400000;
                qtdDias = (l == 1) ? Long.toString(l) + " dia" : Long.toString(l) + " dias";
            } else {
                qtdDias = "Indeterminado";
            }

            //Data Inicial e Final
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String dataInicial, datafinal;

            date.setTime(alarme.getData_inicial());
            dataInicial = sdf.format(date);
            if(alarme.getData_final() == 0) {
                datafinal = "Tempo indeterminado";
            }else{
                date.setTime(alarme.getData_final());
                datafinal = sdf.format(date);
            }

            lstMedicamento.add(new Produto(String.valueOf(listaDeMedicamento.get(i).getId_med()), "Intervalo", (alarme.getIntervalo() == 1) ? "1 vez ao dia" : String.valueOf(alarme.getIntervalo()) + " vezes ao dia", "Quantidade de Dias", qtdDias, "Data Inicial", dataInicial, "Data Final", datafinal));

            // cria o "relacionamento" dos grupos com seus itens
            lstItensGrupo.put(lstGrupos.get(i), lstMedicamento);

        }
        // cria um adaptador (BaseExpandableListAdapter) com os dados acima
        Adaptador2 adaptador = new Adaptador2(this, lstGrupos, lstItensGrupo);

        // define o apadtador do ExpandableListView
        elvCompra.setAdapter(adaptador);

    }

    class Adaptador2 extends BaseExpandableListAdapter {
        private List<String> lstGrupos;
        private HashMap<String, List<Produto>> lstItensGrupos;
        private Context context;


        public Adaptador2(Context context, List<String> grupos, HashMap<String, List<Produto>> itensGrupos) {
            // inicializa as variáveis da classe
            this.context = context;
            lstGrupos = grupos;
            lstItensGrupos = itensGrupos;

        }

        @Override
        public int getGroupCount() {
            // retorna a quantidade de grupos
            return lstGrupos.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            // retorna a quantidade de itens de um grupo
            return lstItensGrupos.get(getGroup(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            // retorna um grupo
            return lstGrupos.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            // retorna um item do grupo
            return lstItensGrupos.get(getGroup(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            // retorna o id do grupo, porém como nesse exemplo
            // o grupo não possui um id específico, o retorno
            // será o próprio groupPosition
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            // retorna o id do item do grupo, porém como nesse exemplo
            // o item do grupo não possui um id específico, o retorno
            // será o próprio childPosition
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            // retorna se os ids são específicos (únicos para cada
            // grupo ou item) ou relativos
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            // cria os itens principais (grupos)

            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.grupo, null);
            }
            ImageView pictograma = (ImageView) convertView.findViewById(R.id.pictograma);


            String caracter = (String) getGroup(groupPosition);
            String array[] = new String[3];


            array = caracter.split("!");



            String draw = "ic_pic" + array[1];
            int drawableIds = getResources().getIdentifier(draw, "drawable", getPackageName());
            drawable = convertView.getResources().getDrawable(drawableIds);
            pictograma.setImageDrawable(drawable);


            TextView tvGrupo = (TextView) convertView.findViewById(R.id.tvGrupo);
            tvGrupo.setText(array[0]);

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            // cria os subitens (itens dos grupos)

            final Produto produto = (Produto) getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.item_grupo_editar, null);
            }

            TextView tvIntervalo = (TextView) convertView.findViewById(R.id.tvIntervalo);
            TextView tvIntervaloValor = (TextView) convertView.findViewById(R.id.tvIntervaloValor);
            TextView tvQtdDias = (TextView) convertView.findViewById(R.id.tvQtdDias);
            TextView tvQtdDiasValor = (TextView) convertView.findViewById(R.id.tvQtdDiasValor);
            TextView tvDataInicial = (TextView) convertView.findViewById(R.id.tvDataInicial);
            TextView tvDataInicialValor = (TextView) convertView.findViewById(R.id.tvDataInicialValor);
            TextView tvDataFinal = (TextView) convertView.findViewById(R.id.tvDataFinal);
            TextView tvDataFinalValor = (TextView) convertView.findViewById(R.id.tvDataFinalValor);
            Button editar = (Button) convertView.findViewById(R.id.tvedit);

            tvIntervalo.setText(produto.getIntervalo());
            tvIntervaloValor.setText(String.valueOf(produto.getValorIntervalo()));
            tvQtdDias.setText(produto.getQtdDias());
            tvQtdDiasValor.setText(String.valueOf(produto.getValorQtdDias()));
            tvDataInicial.setText(produto.getDataInicial());
            tvDataInicialValor.setText(produto.getValorDataInicial());
            tvDataFinal.setText(produto.getDataFinal());
            tvDataFinalValor.setText(produto.getValorDataFinal());

            editar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    btnListaMedicamento2(produto.getId());
                }
            });

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // retorna se o subitem (item do grupo) é selecionável
            return true;
        }

    }


    public void voltar(View view) {
        finish();
        overridePendingTransition(0, R.anim.mover_direita);
    }

    public void btnListaMedicamento2(String s) {
        Intent intent = new Intent(this, ListaMedicamento2.class);
        intent.putExtra("id", s);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.mover_esquerda, R.anim.fade_out);
        ActivityCompat.startActivity(this, intent, activityOptionsCompat.toBundle());
    }

    public void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}