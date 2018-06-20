package br.com.escala.app.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import br.com.escala.app.R;
import br.com.escala.app.adapters.AdapterRelatedMagazine;
import br.com.escala.app.helper.Constantes;
import br.com.escala.app.helper.ImageUtil;
import br.com.escala.app.model.MagazineContentsRelated;
import br.com.escala.app.model.Revista;

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

    private RecyclerView recyclerView;
    private AdapterRelatedMagazine adapter;
    private List<Revista> revistaList;
    private Revista revista;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerView(R.layout.activity_ler_edicao_mes);

        revista = ApresentacaoRevistaActivity.revistaIntent;

//        initBundle();
        initView();
        settingsAdapter();
//        getMagazineDetail();



        botaoLerEdicao.setOnClickListener(v -> {

            Intent intent = new Intent(this, PdfViewActivity.class);
            intent.putExtra("URL", Constantes.URL_BASE_PDF);
            intent.putExtra("PATH", pdfFree);
            intent.putExtra("FILE_NAME", fileName);
            startActivity(intent);
        });

        ImageUtil.loadImage(Constantes.URL_BASE_COVER + revista.getImage(), imgCover, progressBar, R.drawable.logo);
        txtDescricao.setText(revista.getDescricao());

    }


//    private void getMagazineDetail() {
//
//        RestClient.getInstance().magazines().enqueue(new Callback<MagazineRespose>() {
//            @Override
//            public void onResponse(Call<MagazineRespose> call, Response<MagazineRespose> response) {
//
//                if (response.isSuccessful() && response.body() != null){
//
//                    adapter.setRevistaList(response.body().getRevistas());
////                    adapter.setRevistaList(response.body().getRevistas());
//                }else {
//
//                    System.out.println("ERRO ao carregar os dados das descrições");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MagazineRespose> call, Throwable t) {
//
//                Toast.makeText(LerEdicaoMesActivity.this, "Erro ao carregar Dados", Toast.LENGTH_SHORT).show();
//            }
//        });
////    }

//    private void initBundle() {

//        Revista revista =  ApresentacaoRevistaActivity.revistaIntent;
//        Bundle extra = getIntent().getExtras();

//        if( extra != null ){
//            logo = getIntent().getStringExtra("LOGOTIPO");
//            dataLancamento = getIntent().getStringExtra("LANCAMENTO");
//            descricao = getIntent().getStringExtra("DESCRICAO");
//            imageCover = getIntent().getStringExtra("COVER");
//            pdfFree = getIntent().getStringExtra("PDF_FREE");
//            contentOnLine = getIntent().getStringExtra("URL_ONLINE");
//            url = getIntent().getStringExtra("URL");
//            fileName = getIntent().getStringExtra("FILE_NAME");
//            path = getIntent().getStringExtra("PATH");
//        }
//    }

    private void initView() {



        botaoLerEdicao =  findViewById(R.id.btn_ler_edicao_mes_detalhe_id);
        imgCover = findViewById(R.id.image_edicao_detalhe_id);
        txtDescricao = findViewById(R.id.txt_descricao);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recyclerView_id);
    }

    private void settingsAdapter() {

        List<MagazineContentsRelated> magazineContentsRelateds = ApresentacaoRevistaActivity.revistaIntent.getMagazineContentsRelated();

        adapter = new AdapterRelatedMagazine();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setRevistaList(magazineContentsRelateds);
    }

}
