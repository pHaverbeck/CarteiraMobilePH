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

    public DataBaseHelper(@Nullable Context context) {
        super(context, "usuarios.db", null, 1);
    }

    // Cria nova database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABELA_USUARIO + " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_NOME_USUARIO + " TEXT)";

        db.execSQL(createTable);
    }

    // Atualiza database quando há modificações
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

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

    public boolean deletarUm (Usuario usuario) {

        // Busca usuario na database, se encontrar deleta
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABELA_USUARIO + "WHERE " + COLUNA_ID + " = " + usuario.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
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

}
