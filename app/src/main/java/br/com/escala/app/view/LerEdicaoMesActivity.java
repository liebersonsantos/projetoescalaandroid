package br.com.escala.app.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.List;

import br.com.escala.app.R;
import br.com.escala.app.adapters.AdapterRelatedMagazine;
import br.com.escala.app.helper.Constantes;
import br.com.escala.app.helper.ImageUtil;
import br.com.escala.app.model.MagazineContentsRelated;
import br.com.escala.app.model.Revista;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LerEdicaoMesActivity extends AppCompatActivity {

    @BindView(R.id.btn_ler_edicao_mes_detalhe_id)
    Button botaoLerEdicao;
    @BindView(R.id.image_edicao_detalhe_id)
    ImageView imgCover;
    @BindView(R.id.img_logo_revista)
    ImageView imgLogo;
    @BindView(R.id.txt_descricao)
    TextView txtDescricao;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.progressBar_toolbar)
    ProgressBar progressBarToolbar;
    @BindView(R.id.recyclerView_id)
    RecyclerView recyclerView;

    private AdapterRelatedMagazine adapter;
    private List<Revista> revistaList;
    public static Revista revistaIntent;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ler_edicao_mes);

        ButterKnife.bind(this);

        revistaIntent = new Revista();

        Bundle extra = getIntent().getExtras();

        if( extra != null ){
            revistaIntent = getIntent().getExtras().getParcelable("REVISTA");
        }

        settingsAdapter();

        ImageUtil.loadImage(Constantes.URL_BASE_LOGO + revistaIntent.getImageLogo(), imgLogo, progressBarToolbar, R.drawable.logo);
        ImageUtil.loadImage(Constantes.URL_BASE_COVER + revistaIntent.getImage(), imgCover, progressBar, R.drawable.logo);

        txtDescricao.setText(revistaIntent.getDescricao() + "...");

        botaoLerEdicao.setOnClickListener(v -> {

            Intent intent = new Intent(this, ApresentacaoRevistaActivity.class);
            startActivity(intent);
        });

    }

    private void settingsAdapter() {

        List<MagazineContentsRelated> magazineContentsRelateds = revistaIntent.getMagazineContentsRelated();

        adapter = new AdapterRelatedMagazine();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setRevistaList(magazineContentsRelateds);
    }

}
