package com.probex.medicalerta.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.probex.medicalerta.adapter.Alarme;
import com.probex.medicalerta.adapter.Medicamento;

import java.util.ArrayList;
import java.util.List;

public class BancoDadosMed extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_MEDICAMENTO = "db_medicalerta";

    private static final String TABELA_MEDICAMENTO = "tb_medicamento";

    private static final String COLUNA_ID_MED = "id_med";
    private static final String COLUNA_INDICACAO = "indicacao";
    private static final String COLUNA_SUBSTANCIA = "substancia";
    private static final String COLUNA_PRODUTO = "produto";
    private static final String COLUNA_CONCENTRACAO = "concentracao";
    private static final String COLUNA_FORMA_FARMACEUTICA = "forma_farmaceutica";
    private static final String COLUNA_QUANTIDADE = "quantidade";
    private static final String COLUNA_SUBCLASSE = "subclasse";

    //Tabela alarme
    private static final String TABELA_ALARME = "tb_alarme";

    private static final String COLUNA_ID_ALARME = "id_alarme";
    private static final String COLUNA_ID_MEDE = "id_med";
    private static final String COLUNA_DATA_INICIAL = "data_inicial";
    private static final String COLUNA_DATA_FINAL = "data_final";
    private static final String COLUNA_ULTIMO_ALARME = "ultimo_alarme";
    private static final String COLUNA_INTERVALO = "intervalo";


    public BancoDadosMed(@Nullable Context context) {
        super(context, BANCO_MEDICAMENTO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_COLUNA = " CREATE TABLE " + TABELA_MEDICAMENTO + " ( "
                + COLUNA_ID_MED + "  INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUNA_INDICACAO + " INTEGER,"
                + COLUNA_SUBSTANCIA + " TEXT, "
                + COLUNA_PRODUTO + " TEXT,"
                + COLUNA_CONCENTRACAO + " TEXT,"
                + COLUNA_FORMA_FARMACEUTICA + " TEXT,"
                + COLUNA_QUANTIDADE + " TEXT,"
                + COLUNA_SUBCLASSE + " TEXT)";
        db.execSQL(QUERY_COLUNA);

        QUERY_COLUNA = " CREATE TABLE " + TABELA_ALARME + " ( "
                + COLUNA_ID_ALARME + "  INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUNA_ID_MEDE + " INTEGER REFERENCES tb_medicamento (id_med) ON DELETE CASCADE ON UPDATE CASCADE,"
                + COLUNA_DATA_INICIAL + " INTEGER,"
                + COLUNA_DATA_FINAL + " INTEGER,"
                + COLUNA_ULTIMO_ALARME + " INTEGER,"
                + COLUNA_INTERVALO + " INTEGER)";
        db.execSQL(QUERY_COLUNA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addMedicamento(Medicamento medicamento) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_ID_MED, medicamento.getId_med());
        values.put(COLUNA_INDICACAO, medicamento.getIndicacao());
        values.put(COLUNA_SUBSTANCIA, medicamento.getSubstancia());
        values.put(COLUNA_PRODUTO, medicamento.getProduto());
        values.put(COLUNA_CONCENTRACAO, medicamento.getConcentracao());
        values.put(COLUNA_FORMA_FARMACEUTICA, medicamento.getForma_farmaceutica());
        values.put(COLUNA_QUANTIDADE, medicamento.getQuantidade());
        values.put(COLUNA_SUBCLASSE, medicamento.getSubclasse());

        db.insert(TABELA_MEDICAMENTO, null, values);
        db.close();
    }

    public void apagarMedicamento(Medicamento medicamento) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_MEDICAMENTO, COLUNA_ID_MED + " = ? ", new String[]{String.valueOf(medicamento.getId_med())});
        db.close();
    }

    public Medicamento selecionarMedicamento(int id_med) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_MEDICAMENTO, new String[]{COLUNA_ID_MED, COLUNA_INDICACAO,
                COLUNA_SUBSTANCIA, COLUNA_PRODUTO, COLUNA_CONCENTRACAO, COLUNA_FORMA_FARMACEUTICA,
                COLUNA_QUANTIDADE, COLUNA_SUBCLASSE}, COLUNA_ID_MED +
                " = ? ", new String[]{String.valueOf(id_med)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Medicamento medicamento = new Medicamento(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));

        return medicamento;
    }

    public void atualizaMedicamento(Medicamento medicamento) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_INDICACAO, medicamento.getIndicacao());
        values.put(COLUNA_SUBSTANCIA, medicamento.getSubstancia());
        values.put(COLUNA_PRODUTO, medicamento.getProduto());
        values.put(COLUNA_CONCENTRACAO, medicamento.getConcentracao());
        values.put(COLUNA_FORMA_FARMACEUTICA, medicamento.getForma_farmaceutica());
        values.put(COLUNA_QUANTIDADE, medicamento.getQuantidade());
        values.put(COLUNA_SUBCLASSE, medicamento.getSubclasse());

        db.update(TABELA_MEDICAMENTO, values, COLUNA_ID_MED + " = ?", new String[]{String.valueOf(medicamento.getId_med())});
    }

    public List<Medicamento> listaTodosMedicamentos() {
        List<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();

        String query = "SELECT * FROM " + TABELA_MEDICAMENTO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Medicamento medicamento = new Medicamento();
                medicamento.setId_med(Integer.parseInt(c.getString(0)));
                medicamento.setIndicacao(Integer.parseInt(c.getString(1)));
                medicamento.setSubstancia(c.getString(2));
                medicamento.setProduto(c.getString(3));
                medicamento.setConcentracao(c.getString(4));
                medicamento.setForma_farmaceutica(c.getString(5));
                medicamento.setQuantidade(c.getString(6));
                medicamento.setSubclasse(c.getString(7));

                listaMedicamentos.add(medicamento);
            } while (c.moveToNext());
        }
        return listaMedicamentos;

    }

    //ALARME

    public void addAlarme(Alarme alarme) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_ID_MEDE, alarme.getId_med());
        values.put(COLUNA_DATA_INICIAL, alarme.getData_inicial());
        values.put(COLUNA_DATA_FINAL, alarme.getData_final());
        values.put(COLUNA_ULTIMO_ALARME, alarme.getUltimo_alarme());
        values.put(COLUNA_INTERVALO, alarme.getIntervalo());

        db.insert(TABELA_ALARME, null, values);
        db.close();
    }

    public void apagarAlarme(Alarme alarme) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_ALARME, COLUNA_ID_ALARME + " = ? ", new String[]{String.valueOf(alarme.getId_alarme())});
        db.close();
    }

    Alarme selecionarAlarme(int id_alarme) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_ALARME, new String[]{COLUNA_ID_ALARME, COLUNA_ID_MED, COLUNA_DATA_INICIAL,
                COLUNA_DATA_FINAL, COLUNA_ULTIMO_ALARME, COLUNA_INTERVALO}, COLUNA_ID_ALARME +
                " = ? ", new String[]{String.valueOf(id_alarme)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Alarme alarme = new Alarme(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), Long.parseLong(cursor.getString(2)), Long.parseLong(cursor.getString(3)), Long.parseLong(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));

        return alarme;
    }

    public void atualizaAlarme(Alarme alarme) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_ID_MEDE, alarme.getId_med());
        values.put(COLUNA_DATA_INICIAL, alarme.getData_inicial());
        values.put(COLUNA_DATA_FINAL, alarme.getData_final());
        values.put(COLUNA_ULTIMO_ALARME, alarme.getUltimo_alarme());
        values.put(COLUNA_INTERVALO, alarme.getIntervalo());

        db.update(TABELA_ALARME, values, COLUNA_ID_ALARME + " = ?", new String[]{String.valueOf(alarme.getId_alarme())});
    }

    public List<Alarme> listaTodosAlarmes() {
        List<Alarme> listaAlarmes = new ArrayList<Alarme>();

        String query = "SELECT * FROM " + TABELA_ALARME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Alarme alarme = new Alarme();
                alarme.setId_alarme(Integer.parseInt(c.getString(0)));
                alarme.setId_med(Integer.parseInt(c.getString(1)));
                alarme.setData_inicial(Long.parseLong(c.getString(2)));
                alarme.setData_final(Long.parseLong(c.getString(3)));
                alarme.setUltimo_alarme(Long.parseLong(c.getString(4)));
                alarme.setIntervalo(Integer.parseInt(c.getString(5)));
                listaAlarmes.add(alarme);
            } while (c.moveToNext());
        }
        return listaAlarmes;

    }

}
