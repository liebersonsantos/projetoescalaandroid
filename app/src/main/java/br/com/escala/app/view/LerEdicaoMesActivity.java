package br.com.escala.app.view;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import br.com.escala.app.R;
import br.com.escala.app.helper.Constantes;
import br.com.escala.app.helper.ImageUtil;

public class LerEdicaoMesActivity extends BaseActivity {

    private int id;
    private String logo;
    private String dataLancamento;
    private String descricao;
    private String imageCover;
    private String pdfFree;
    private String contentOnLine;
    private String path;
    private String fileName = "";
    private String url;

    private Button botaoLerEdicao;
    private ImageView imgCover;
    private TextView txtDescricao;
    private ProgressBar progressBar;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerView(R.layout.activity_ler_edicao_mes);

        Bundle extra = getIntent().getExtras();

        if( extra != null ){
            logo = getIntent().getStringExtra("LOGOTIPO");
            dataLancamento = getIntent().getStringExtra("LANCAMENTO");
            descricao = getIntent().getStringExtra("DESCRICAO");
            imageCover = getIntent().getStringExtra("COVER");
            pdfFree = getIntent().getStringExtra("PDF_FREE");
            contentOnLine = getIntent().getStringExtra("URL_ONLINE");
            url = getIntent().getStringExtra("URL");
            fileName = getIntent().getStringExtra("FILE_NAME");
            path = getIntent().getStringExtra("PATH");
        }

        botaoLerEdicao =  findViewById(R.id.btn_ler_edicao_mes_detalhe_id);
        imgCover = findViewById(R.id.image_edicao_detalhe_id);
        txtDescricao = findViewById(R.id.txt_descricao);
        progressBar = findViewById(R.id.progress_bar);

        botaoLerEdicao.setOnClickListener(v -> {

            Intent intent = new Intent(this, PdfViewActivity.class);
            intent.putExtra("URL", Constantes.URL_BASE_PDF);
            intent.putExtra("PATH", pdfFree);
            intent.putExtra("FILE_NAME", fileName);
            startActivity(intent);
        });

        ImageUtil.loadImage(Constantes.URL_BASE_COVER + imageCover, imgCover, progressBar, R.drawable.logo);
        txtDescricao.setText(descricao);


    }

}
