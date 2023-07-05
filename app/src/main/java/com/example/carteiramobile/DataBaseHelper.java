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

    //Tabela e Colunas para USUARIO
    public static final String TABELA_USUARIO = "TABELA_USUARIO";
    public static final String COLUNA_NOME_USUARIO = "NOME_USUARIO";
    public static final String COLUNA_ID = "ID";

    // Tabela e Colunas para OPERACAO
    public static final String TABELA_OPERACAO = "TABELA_OPERACAO";

    public static final String COLUNA_ID_OPERACAO = "ID_OPERACAO";

    public static final String COLUNA_ATIVO = "ATIVO";

    public static final String COLUNA_QUANTIDADE = "QUANTIDADE";

    public static final String COLUNA_VALOR = "VALOR";

    public static final String COLUNA_DATA = "DATA";

    // Criação da base de dados, ficou com o nome de "usuarios.db"
    public DataBaseHelper(@Nullable Context context) {
        super(context, "usuarios.db", null, 1);
    }

    // Preenche nova base de dados
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Comando que irá criar a primeira tabela (USUARIO e seus conteúdos)
        String createTable = "CREATE TABLE " + TABELA_USUARIO + " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_NOME_USUARIO + " TEXT)";

        // Comando que irá criar a segunda tabela (OPERACAO e seus conteúdos)
        String createTableOp = "CREATE TABLE " + TABELA_OPERACAO + " (" + COLUNA_ID_OPERACAO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_ATIVO + " TEXT, " + COLUNA_QUANTIDADE + " INTEGER, " + COLUNA_VALOR + " INTEGER, " + COLUNA_DATA + " DATE)";

        // Executa os comandos declarados anteriormente e cria as tabelas
        // Obs: Por algum motivo que não consegui identificar, a segunda tabela (OPERACAO) não está sendo criada na base de dados
        db.execSQL(createTable);
        db.execSQL(createTableOp);
    }

    // Atualiza base de dados quando há modificações
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_OPERACAO);
        onCreate(db);
    }

    // Método de CREATE para o CRUD de Usuários
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

    // Método de CREATE para o CRUD de Operações
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

    // Método de UPDATE para o CRUD de Usuários
    // Não encontrei nenhum tutorial, video ou fórum que me ensinasse como implementar esta parte do CRUD do jeito que eu queria
    // Foram diversas tentativas, pois eu queria fazer o aplicativo do meu jeito, e não copiar de alguém, mas não tive sucesso
    // Apesar de não ter dado certo, deixei a última tentativa em comentário abaixo

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

    // Método de DELETE para o CRUD de Usuários
    public long deletarUm (Usuario usuario) {

        // Busca usuario na database, se encontrar deleta
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABELA_USUARIO, "id = ?", new String[]{String.valueOf(usuario.getId())});
        db.close();
        return id;
    }

    // Método de READ para o CRUD de Usuários
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


    // Método de READ para o CRUD de Operações (Apesar de o código estar aqui, a tabela de Operações por algum motivo
    // não está sendo criada ao executar, e portanto, não consegui verificar o correto funcionamento dos dois primeiros métodos.
    // Sendo assim, os métodos de Update e Delete nem foram escritos aqui, porém, seriam similares aos já feitos para o CRUD de Usuários
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
