package br.com.escala.app.view;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.escala.app.R;
import br.com.escala.app.helper.Constantes;
import br.com.escala.app.helper.ImageUtil;
import br.com.escala.app.model.Revista;

public class ApresentacaoRevistaActivity extends AppCompatActivity {

    private ImageView imgLogo, imgCover;
    private TextView txtLancamento;
    private Button btnMonthEdition, btnLastEdition, btnContentOnLine;
    private ProgressBar progressBar, progressBarToolbar;
    private Revista revista;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao_revista);

        revista = LerEdicaoMesActivity.revistaIntent;

        initViews();


        ImageUtil.loadImage(Constantes.URL_BASE_COVER + revista.getImage(), imgCover, progressBar, R.drawable.logo);
        ImageUtil.loadImage(Constantes.URL_BASE_LOGO + revista.getImageLogo(), imgLogo, progressBarToolbar, R.drawable.logo);
        txtLancamento.setText(revista.getDataLancamento());



        btnContentOnLine.setOnClickListener(v -> {
            Intent intent = new Intent(this, WebActivity.class);
            intent.putExtra("URL_ONLINE", revista.getContentOnLine());
            startActivity(intent);

        });

        btnMonthEdition.setOnClickListener(v -> {
            Intent intent = new Intent(this, PdfViewActivity.class);
            intent.putExtra("URL", Constantes.URL_BASE_PDF);
            intent.putExtra("PATH", revista.getUrlPdfFree());
            intent.putExtra("FILE_NAME", revista.getId() + ".pdf");
            startActivity(intent);

        });

    }

    private void initViews() {

        imgLogo = findViewById(R.id.img_logo_revista);
        imgCover = findViewById(R.id.imagem_detalhe_Id);
        txtLancamento = findViewById(R.id.txt_edition_month);
        btnMonthEdition = findViewById(R.id.btn_edicao_mes_id);
        btnLastEdition = findViewById(R.id.btn_edicao_anterior_id);
        btnContentOnLine = findViewById(R.id.btn_conteudo_on_id);
        progressBar = findViewById(R.id.progress_bar_category);
        progressBarToolbar = findViewById(R.id.progressBar_toolbar);
    }
}

