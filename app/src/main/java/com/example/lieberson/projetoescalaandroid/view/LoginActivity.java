package com.example.lieberson.projetoescalaandroid.view;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lieberson.projetoescalaandroid.R;

public class LoginActivity extends AppCompatActivity {

    private TextView textCadastro;
    private AppCompatEditText senhaCadastro;
    private AppCompatEditText emailCadastro;
    private Button botaoLogar;

    private android.support.v7.widget.Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        toolbar = findViewById(R.id.toolbarId);
//        toolbar.inflateMenu(R.menu.menu_toolbar);

        emailCadastro = findViewById(R.id.edit_text_login_id);
        senhaCadastro = findViewById(R.id.edit_senha_id);
        textCadastro = findViewById(R.id.text_faca_cadastro);
        botaoLogar = findViewById(R.id.btn_logar_id);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (emailCadastro.getText().toString().equals("teste") &&
                        senhaCadastro.getText().toString().equals("1234")){

                    Intent intent = new Intent(LoginActivity.this, ApresentacaoActivity.class);
                    startActivity(intent);
                    finish();

                }else {

                    Toast.makeText(LoginActivity.this, "ERRO", Toast.LENGTH_SHORT).show();
                }

            }
        });

        textCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                    startActivity(intent);
                    finish();

                }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        return true;
    }
}
