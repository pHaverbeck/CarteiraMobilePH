package com.example.carteiramobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carteiramobile.DataBaseHelper;
import com.example.carteiramobile.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Botões e Interações
    Button btCriar, btListar, btEditar, btDeletar, btLogin;
    EditText etNomeUsuario;
    ListView listaUsuarios;

    ArrayAdapter usuarioArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCriar = findViewById(R.id.btCriar);
        btListar = findViewById(R.id.btListar);
        btEditar = findViewById(R.id.btEditar);
        btDeletar = findViewById(R.id.btDeletar);
        btLogin= findViewById(R.id.btLogin);
        etNomeUsuario = findViewById(R.id.etNomeUsuario);
        listaUsuarios = findViewById(R.id.listaUsuarios);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        mostrarLista(dataBaseHelper);

        // Click listeners botões
        btCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario usuario;

                try {
                    usuario = new Usuario(-1, etNomeUsuario.getText().toString());
                    Toast.makeText(MainActivity.this, usuario.toString(), Toast.LENGTH_SHORT).show();
                }

                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Usuário Inválido", Toast.LENGTH_SHORT).show();
                    usuario = new Usuario (-1, "error");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean msgSucesso = dataBaseHelper.criarUm(usuario);
                Toast.makeText(MainActivity.this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                mostrarLista(dataBaseHelper);
            }
        });

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                mostrarLista(dataBaseHelper);
            }
        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Botão Editar", Toast.LENGTH_SHORT).show();
            }
        });

        btDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Botão Deletar", Toast.LENGTH_SHORT).show();
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Botão Login", Toast.LENGTH_SHORT).show();
            }
        });

        listaUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Usuario usuarioClicado = (Usuario) parent.getItemAtPosition(position);
                dataBaseHelper.deletarUm(usuarioClicado);
                mostrarLista(dataBaseHelper);

                Toast.makeText(MainActivity.this, "Usuario deletado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Método usado para Lista aparecer ao abrir app ou ao criar novo usuario, já atualizada
    private void mostrarLista(DataBaseHelper dataBaseHelper) {
        usuarioArrayAdapter = new ArrayAdapter<Usuario>(MainActivity.this, android.R.layout.simple_selectable_list_item, dataBaseHelper.getEveryone());
        listaUsuarios.setAdapter(usuarioArrayAdapter);
    }
}