package com.probex.medicalerta.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.probex.medicalerta.activity.Medicamento;

import java.util.ArrayList;
import java.util.List;

public class BancoListaMed extends SQLiteOpenHelper {
    public static final String NOMEDB = "db_lista_medicamentos";
    public static final String LOCALDB = "/data/data/com.probex.medicalerta/databases/";
    public static final int VERSION = 1;
    private Context mContext;
    private SQLiteDatabase sqLiteDatabase;


    public BancoListaMed(@Nullable Context context) {
        super(context, NOMEDB, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(NOMEDB).getPath();
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            return;
        }
        sqLiteDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDataBase() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
    }

    public List<Medicamento> allMedicamento(String nome) {
        openDatabase();
        sqLiteDatabase = this.getWritableDatabase();
        List<Medicamento> listMedicamento = new ArrayList<Medicamento>();
        String sql = "SELECT * FROM tb_lista_medicamentos WHERE produto = '" + nome + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Medicamento medicamento = new Medicamento();
                    medicamento.setId_med(Integer.parseInt(cursor.getString(0)));
                    medicamento.setIndicacao(Integer.parseInt(cursor.getString(1)));
                    medicamento.setSubstancia(cursor.getString(2));
                    medicamento.setProduto(cursor.getString(3));
                    medicamento.setConcentracao(cursor.getString(4));
                    medicamento.setForma_farmaceutica(cursor.getString(5));
                    medicamento.setQuantidade(cursor.getString(6));
                    medicamento.setSubclasse(cursor.getString(7));

                    listMedicamento.add(medicamento);

                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return listMedicamento;
    }
}

