package br.com.escala.app.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.escala.app.R;
import br.com.escala.app.helper.Constantes;
import br.com.escala.app.helper.ImageUtil;
import br.com.escala.app.model.Revista;

public class ApresentacaoRevistaActivity extends BaseActivity {

    private ImageView imgLogoTipo, imgCover;
    private TextView txtLancamento;
    private Button btnMonthEdition, btnLastEdition, btnContentOnLine;
    private ProgressBar progressBar, progressBarT;
    private Revista revista;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerView(R.layout.activity_apresentacao_revista);

        revista = LerEdicaoMesActivity.revistaIntent;


        imgLogoTipo = findViewById(R.id.img_logo_toolbar);
        imgCover = findViewById(R.id.imagem_detalhe_Id);
        txtLancamento = findViewById(R.id.txt_edition_month);
        btnMonthEdition = findViewById(R.id.btn_edicao_mes_id);
        btnLastEdition = findViewById(R.id.btn_edicao_anterior_id);
        btnContentOnLine = findViewById(R.id.btn_conteudo_on_id);
        progressBar = findViewById(R.id.progress_bar);

        ImageUtil.loadImage(Constantes.URL_BASE_COVER + revista.getImage(), imgCover, progressBar, R.drawable.logo);
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
}

