package com.example.carteiramobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TABELA_USUARIO = "TABELA_USUARIO";
    public static final String COLUNA_NOME_USUARIO = "NOME_USUARIO";
    public static final String COLUNA_ID = "ID";

    public static final String TABELA_OPERACAO = "TABELA_OPERACAO";

    public static final String COLUNA_ID_OPERACAO = "ID_OPERACAO";

    public static final String COLUNA_ATIVO = "ATIVO";

    public static final String COLUNA_QUANTIDADE = "QUANTIDADE";

    public static final String COLUNA_VALOR = "VALOR";

    public static final String COLUNA_DATA = "DATA";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "usuarios.db", null, 1);
    }

    // Cria nova database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABELA_USUARIO + " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_NOME_USUARIO + " TEXT)";

        String createTableOp = "CREATE TABLE " + TABELA_OPERACAO + " (" + COLUNA_ID_OPERACAO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_ATIVO + " TEXT, " + COLUNA_QUANTIDADE + " INTEGER, " + COLUNA_VALOR + " INTEGER, " + COLUNA_DATA + " DATE)";

        db.execSQL(createTable);
        db.execSQL(createTableOp);

    }

    // Atualiza database quando há modificações
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_OPERACAO);
        onCreate(db);
    }

    public boolean criarUm (Usuario usuario) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUNA_NOME_USUARIO, usuario.getName());

        long insert = db.insert(TABELA_USUARIO, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }

    }

    public boolean criarOp (Operacoes operacoes) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvOp = new ContentValues();

        cvOp.put(COLUNA_ATIVO, operacoes.getAtivo());
        cvOp.put(COLUNA_QUANTIDADE, operacoes.getQuantidade());
        cvOp.put(COLUNA_VALOR, operacoes.getValor());
        cvOp.put(COLUNA_DATA, operacoes.getData());

        long insert = db.insert(TABELA_OPERACAO, null, cvOp);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }

    }

    /*
    public long editarUm (Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUNA_NOME_USUARIO, usuario.getName());

        long insert = db.update(TABELA_USUARIO, cv, "id = ?", new String[]{String.valueOf(usuario.getId())});
        db.close();
        return insert;
    }
    */

    public long deletarUm (Usuario usuario) {

        // Busca usuario na database, se encontrar deleta
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABELA_USUARIO, "id = ?", new String[]{String.valueOf(usuario.getId())});
        db.close();
        return id;
    }

    public List<Usuario> getEveryone() {

        List<Usuario> retornaLista = new ArrayList<>();

        // Obtém dados da database
        String queryString = "SELECT * FROM " + TABELA_USUARIO;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {

            do {
                int usuarioID = cursor.getInt(0);
                String usuarioName = cursor.getString(1);

                Usuario novoUsuario = new Usuario(usuarioID, usuarioName);
                retornaLista.add (novoUsuario);

            } while (cursor.moveToNext());
        }
        else {

        }

        cursor.close();
        db.close();
        return retornaLista;
    }

    public List<Operacoes> getAllOperacoes() {

        List<Operacoes> retornaListaOp = new ArrayList<>();

        // Obtém dados da database
        String queryString = "SELECT * FROM " + TABELA_OPERACAO;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {

            do {
                int operacaoID = cursor.getInt(0);
                String operacaoAtivo = cursor.getString(1);
                int operacaoQuantidade = Integer.parseInt(cursor.getString(2));
                int operacaoValor = Integer.parseInt(cursor.getString(3));
                String operacaoData = cursor.getString(4);

                Operacoes novaOperacao = new Operacoes(operacaoID, operacaoAtivo, operacaoQuantidade, operacaoValor, operacaoData);
                retornaListaOp.add (novaOperacao);

            } while (cursor.moveToNext());
        }
        else {

        }

        cursor.close();
        db.close();
        return retornaListaOp;
    }

}
