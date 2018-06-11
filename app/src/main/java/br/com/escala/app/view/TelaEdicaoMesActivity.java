package br.com.escala.app.view;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.escala.app.R;

public class TelaEdicaoMesActivity extends AppCompatActivity {

    private ImageView botaoFechar, botaoPdf;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_edicao_mes);

        botaoFechar = findViewById(R.id.imagem_x_id);
        botaoPdf = findViewById(R.id.imagem_tela_pdf_id);

        botaoFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TelaEdicaoMesActivity.this, LerEdicaoMesActivity.class);
                startActivity(intent);
                finish();

            }
        });

        botaoPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /*Dialog*/
                dialog = new Dialog(TelaEdicaoMesActivity.this, R.style.DialogFullscreen);
                dialog.setContentView(R.layout.activity_notificacao);
                ImageView img_dialog_fullscreen_close = dialog.findViewById(R.id.btn_fechar_notificacao_id);

                Button botaoAssine = dialog.findViewById(R.id.btn_assine_ja_id);
                Button botaoCompreApp = dialog.findViewById(R.id.btn_compre_app_id);
                TextView textoTelaLogin = dialog.findViewById(R.id.btn_tela_login_id);

                img_dialog_fullscreen_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                /**/

                botaoAssine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(TelaEdicaoMesActivity.this, AssineJaActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

                botaoCompreApp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(TelaEdicaoMesActivity.this, CompreAppActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

                textoTelaLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(TelaEdicaoMesActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

                dialog.show();
            }

        });
    }
}
