package com.example.carteiramobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CadastroOperacoes extends AppCompatActivity {

    // Botões e interações
    Button btCriarOp, btEditarOp, btDeletarOp;
    EditText etAtivo, etQuantidade, etValor, etData;
    ListView listaOperacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacoes);

        btCriarOp = findViewById(R.id.btCriarOp);
        btEditarOp = findViewById(R.id.btEditarOp);
        btDeletarOp = findViewById(R.id.btDeletarOp);
        etAtivo = findViewById(R.id.etAtivo);
        etQuantidade = findViewById(R.id.etQuantidade);
        etValor = findViewById(R.id.etValor);
        etData = findViewById(R.id.etData);
        listaOperacoes = findViewById(R.id.listaOperacoes);

        // Botão CRIAR
        btCriarOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Operacoes operacoes;

                try {
                    operacoes = new Operacoes(-1, etAtivo.getText().toString(), Integer.parseInt(etQuantidade.getText().toString()), Integer.parseInt(etValor.getText().toString()), etData.getText().toString());
                    // Toast.makeText(CadastroOperacoes.this, operacoes.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(CadastroOperacoes.this, "Preencher todos os campos!", Toast.LENGTH_SHORT).show();
                    operacoes = new Operacoes(-1, "error", 0, 0 ,"error");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(CadastroOperacoes.this);

                boolean b = dataBaseHelper.criarOp(operacoes);
                Toast.makeText(CadastroOperacoes.this, "Operação cadastrada com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        // Botão EDITAR (conforme explicado anteriormente, não funciona, ficou aqui apenas a versão teste)
        btEditarOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CadastroOperacoes.this, "Teste botão editar", Toast.LENGTH_SHORT).show();
            }
        });

        // Botão DELETAR (conforme explicado anteriormente, visto que não funciona a criação da tabela na base de dados, não há o que deletar
        // Ficou aqui apenas a versão teste)
        btDeletarOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CadastroOperacoes.this, "Teste botão deletar", Toast.LENGTH_SHORT).show();
            }
        });

        // OBS: Botão LISTAR nem foi criado nesta tela, pois a ideia era fazer a atualização automática da lista, como na tela anterior
    }
}