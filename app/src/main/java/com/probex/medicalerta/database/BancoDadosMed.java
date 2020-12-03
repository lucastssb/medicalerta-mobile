package com.probex.medicalerta.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.probex.medicalerta.adapter.Alarme;
import com.probex.medicalerta.adapter.Historico;
import com.probex.medicalerta.adapter.Medicamento;
import com.probex.medicalerta.adapter.Notificacao;
import com.probex.medicalerta.adapter.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BancoDadosMed extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_MEDICAMENTO = "db_medicalerta";


    //Tabela Medicamento
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
    private static final String COLUNA_ID_MEDE = "id_mede";
    private static final String COLUNA_DATA_INICIAL = "data_inicial";
    private static final String COLUNA_DATA_FINAL = "data_final";
    private static final String COLUNA_ULTIMO_ALARME = "ultimo_alarme";
    private static final String COLUNA_INTERVALO = "intervalo";

    //Tabela Usuário
    private static final String TABELA_USUARIO = "tb_usuario";
    private static final String COLUNA_ID_USUARIO = "id_usuario";
    private static final String COLUNA_NOME_USUARIO = "nome_usuario";
    private static final String COLUNA_IDADE_USUARIO = "idade_usuario";
    private static final String COLUNA_FOTO_USUARIO = "foto_usuario_";

    //Tabela Notificações
    private static final String TABELA_NOTIFICACAO = "tb_notificacao";
    private static final String COLUNA_ID_NOTIFICACAO = "id_notificacao";
    private static final String COLUNA_NOME_NOTIFICACAO = "nome_notificacao";
    private static final String COLUNA_DESCRICAO_NOTIFICACAO = "descricao_notificacao";

    //Tabela Histórico
    private static final String TABELA_HISTORICO = "tb_historico";
    private static final String COLUNA_ID_HISTORICO = "id_historico";


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

        QUERY_COLUNA = " CREATE TABLE " + TABELA_USUARIO + " ( "
                + COLUNA_ID_USUARIO + "  INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUNA_NOME_USUARIO + " VARCHAR,"
                + COLUNA_IDADE_USUARIO + " INTEGER,"
                + COLUNA_FOTO_USUARIO + " VARCHAR)";
        db.execSQL(QUERY_COLUNA);

        QUERY_COLUNA = " CREATE TABLE " + TABELA_NOTIFICACAO + " ( "
                + COLUNA_ID_NOTIFICACAO + "  INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUNA_NOME_NOTIFICACAO + " VARCHAR,"
                + COLUNA_DESCRICAO_NOTIFICACAO + " TEXT)";
        db.execSQL(QUERY_COLUNA);

        QUERY_COLUNA = " CREATE TABLE " + TABELA_HISTORICO + " ( "
                + COLUNA_ID_HISTORICO + "  INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUNA_ID_MED + "  INTEGER,"
                + COLUNA_INDICACAO + " INTEGER,"
                + COLUNA_SUBSTANCIA + " TEXT, "
                + COLUNA_PRODUTO + " TEXT,"
                + COLUNA_CONCENTRACAO + " TEXT,"
                + COLUNA_FORMA_FARMACEUTICA + " TEXT,"
                + COLUNA_QUANTIDADE + " TEXT,"
                + COLUNA_SUBCLASSE + " TEXT,"
                + COLUNA_DATA_INICIAL + " INTEGER,"
                + COLUNA_DATA_FINAL + " INTEGER,"
                + COLUNA_INTERVALO + " INTEGER)";
        db.execSQL(QUERY_COLUNA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //TABELA MEDICAMENTO
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

    //TABELA ALARME

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

    public Alarme selecionarAlarme(int id_alarme) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_ALARME, new String[]{COLUNA_ID_ALARME, COLUNA_ID_MEDE, COLUNA_DATA_INICIAL,
                COLUNA_DATA_FINAL, COLUNA_ULTIMO_ALARME, COLUNA_INTERVALO}, COLUNA_ID_MEDE +
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

    //TABELA Históricohistorico
    public void addHistorico(Historico historico) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_ID_MED, historico.getId_medicamento());
        values.put(COLUNA_INDICACAO, historico.getIndicacao());
        values.put(COLUNA_SUBSTANCIA, historico.getSubstancia());
        values.put(COLUNA_PRODUTO, historico.getProduto());
        values.put(COLUNA_CONCENTRACAO, historico.getConcentracao());
        values.put(COLUNA_FORMA_FARMACEUTICA, historico.getForma_farmaceutica());
        values.put(COLUNA_QUANTIDADE, historico.getQuantidade());
        values.put(COLUNA_SUBCLASSE, historico.getSubclasse());
        values.put(COLUNA_DATA_INICIAL, historico.getData_inicial());
        values.put(COLUNA_DATA_FINAL, historico.getData_final());
        values.put(COLUNA_INTERVALO, historico.getIntervalo());


        db.insert(TABELA_HISTORICO, null, values);
        db.close();
    }

    public void apagarHistorico(Historico historico) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_HISTORICO, COLUNA_ID_HISTORICO + " = ? ", new String[]{String.valueOf(historico.getId_historico())});
        db.close();
    }

    public Historico selecionarHistorico(int id_historico) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_HISTORICO, new String[]{COLUNA_ID_HISTORICO, COLUNA_ID_MED, COLUNA_INDICACAO,
                COLUNA_SUBSTANCIA, COLUNA_PRODUTO, COLUNA_CONCENTRACAO, COLUNA_FORMA_FARMACEUTICA,
                COLUNA_QUANTIDADE, COLUNA_SUBCLASSE, COLUNA_DATA_INICIAL, COLUNA_DATA_FINAL, COLUNA_INTERVALO}, COLUNA_ID_HISTORICO +
                " = ? ", new String[]{String.valueOf(id_historico)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Historico historico = new Historico(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), Long.parseLong(cursor.getString(9)), Long.parseLong(cursor.getString(10)), Integer.parseInt(cursor.getString(11)));

        return historico;
    }

    public void atualizaHistorico(Historico historico) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_ID_MED, historico.getId_medicamento());
        values.put(COLUNA_INDICACAO, historico.getIndicacao());
        values.put(COLUNA_SUBSTANCIA, historico.getSubstancia());
        values.put(COLUNA_PRODUTO, historico.getProduto());
        values.put(COLUNA_CONCENTRACAO, historico.getConcentracao());
        values.put(COLUNA_FORMA_FARMACEUTICA, historico.getForma_farmaceutica());
        values.put(COLUNA_QUANTIDADE, historico.getQuantidade());
        values.put(COLUNA_SUBCLASSE, historico.getSubclasse());
        values.put(COLUNA_DATA_INICIAL, historico.getData_inicial());
        values.put(COLUNA_DATA_FINAL, historico.getData_final());
        values.put(COLUNA_INTERVALO, historico.getIntervalo());

        db.update(TABELA_HISTORICO, values, COLUNA_ID_HISTORICO + " = ?", new String[]{String.valueOf(historico.getId_historico())});
    }

    public List<Historico> listaTodosHistorico() {
        List<Historico> listaMedicamentos = new ArrayList<Historico>();

        String query = "SELECT * FROM " + TABELA_HISTORICO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Historico historico = new Historico();
                historico.setId_historico(Integer.parseInt(c.getString(0)));
                historico.setId_medicamento(Integer.parseInt(c.getString(1)));
                historico.setIndicacao(Integer.parseInt(c.getString(2)));
                historico.setSubstancia(c.getString(3));
                historico.setProduto(c.getString(4));
                historico.setConcentracao(c.getString(5));
                historico.setForma_farmaceutica(c.getString(6));
                historico.setQuantidade(c.getString(7));
                historico.setSubclasse(c.getString(8));
                historico.setData_inicial(Long.parseLong(c.getString(9)));
                historico.setData_final(Long.parseLong(c.getString(10)));
                historico.setIntervalo(Integer.parseInt(c.getString(11)));


                listaMedicamentos.add(historico);
            } while (c.moveToNext());
        }
        return listaMedicamentos;

    }

    //TABELA NOTIFICAÇÃO
    public void addNotificação(Notificacao notificacao) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_NOTIFICACAO, notificacao.getNome());
        values.put(COLUNA_DESCRICAO_NOTIFICACAO, notificacao.getDescricao());


        db.insert(TABELA_NOTIFICACAO, null, values);
        db.close();
    }

    public void apagarNotificacao(Notificacao notificacao) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_NOTIFICACAO, COLUNA_ID_NOTIFICACAO + " = ? ", new String[]{String.valueOf(notificacao.getId())});
        db.close();
    }

    public Notificacao selecionarNotificacao(int id_notificacao) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_NOTIFICACAO, new String[]{COLUNA_ID_NOTIFICACAO, COLUNA_NOME_NOTIFICACAO, COLUNA_DESCRICAO_NOTIFICACAO}, COLUNA_ID_NOTIFICACAO +
                " = ? ", new String[]{String.valueOf(id_notificacao)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Notificacao notificacao = new Notificacao(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return notificacao;
    }

    public void atualizaNotificacao(Notificacao notificacao) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_ID_NOTIFICACAO, notificacao.getId());
        values.put(COLUNA_NOME_NOTIFICACAO, notificacao.getNome());
        values.put(COLUNA_DESCRICAO_NOTIFICACAO, notificacao.getDescricao());

        db.update(TABELA_NOTIFICACAO, values, COLUNA_ID_NOTIFICACAO + " = ?", new String[]{String.valueOf(notificacao.getId())});
    }

    public List<Notificacao> listaTodasNotificacao() {
        List<Notificacao> listaNotificacao = new ArrayList<Notificacao>();

        String query = "SELECT * FROM " + TABELA_NOTIFICACAO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Notificacao notificacao = new Notificacao();
                notificacao.setId(Integer.parseInt(c.getString(0)));
                notificacao.setNome(c.getString(1));
                notificacao.setDescricao(c.getString(2));
                listaNotificacao.add(notificacao);
            } while (c.moveToNext());
        }
        return listaNotificacao;

    }

    //TABELA Usuario

    public void addUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_USUARIO, usuario.getNome());
        values.put(COLUNA_IDADE_USUARIO, usuario.getIdade());
        values.put(COLUNA_FOTO_USUARIO, usuario.getFoto());


        db.insert(TABELA_USUARIO, null, values);
        db.close();
    }

    public void apagarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_USUARIO, COLUNA_ID_USUARIO + " = ? ", new String[]{String.valueOf(usuario.getId())});
        db.close();
    }

    public Usuario selecionarUsuario(int id_usuario) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_USUARIO, new String[]{COLUNA_ID_USUARIO, COLUNA_NOME_USUARIO, COLUNA_IDADE_USUARIO, COLUNA_FOTO_USUARIO}, COLUNA_ID_USUARIO +
                " = ? ", new String[]{String.valueOf(id_usuario)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Usuario usuario = new Usuario(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3));

        return usuario;
    }

    public void atualizaUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_ID_USUARIO, usuario.getId());
        values.put(COLUNA_NOME_USUARIO, usuario.getNome());
        values.put(COLUNA_IDADE_USUARIO, usuario.getIdade());
        values.put(COLUNA_FOTO_USUARIO, usuario.getFoto());

        db.update(TABELA_USUARIO, values, COLUNA_ID_USUARIO + " = ?", new String[]{String.valueOf(usuario.getId())});
    }

    public List<Usuario> listaTodosUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();

        String query = "SELECT * FROM " + TABELA_USUARIO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                usuario.setId(Integer.parseInt(c.getString(0)));
                usuario.setNome(c.getString(1));
                usuario.setIdade(Integer.parseInt(c.getString(2)));
                usuario.setFoto(c.getString(3));
                listaUsuarios.add(usuario);
            } while (c.moveToNext());
        }
        return listaUsuarios;

    }

}
