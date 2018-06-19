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

    private int id;
    private String logo;
    private String dataLancamento;
    private String imageCover;
    private String descricao;
    private String pdfFree;
    private String contentOnLine;
    private String path;
    private String fileName = "";
    private String url;

    private ImageView imgLogoTipo, imgCover;
    private TextView txtLancamento;
    private Button btnMonthEdition, btnLastEdition, btnContentOnLine;
    private Revista revista;
    private ProgressBar progressBar, progressBarT;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerView(R.layout.activity_apresentacao_revista);

        Bundle extra = getIntent().getExtras();

        if( extra != null ){
            logo = getIntent().getStringExtra("LOGOTIPO");
            dataLancamento = getIntent().getStringExtra("LANCAMENTO");
            imageCover = getIntent().getStringExtra("COVER");
            descricao = getIntent().getStringExtra("DESCRICAO");
            pdfFree = getIntent().getStringExtra("PDF_FREE");
            contentOnLine = getIntent().getStringExtra("URL_ONLINE");
            url = getIntent().getStringExtra("URL");
            fileName = getIntent().getStringExtra("FILE_NAME");
            path = getIntent().getStringExtra("PATH");
        }

        revista = new Revista();

        imgLogoTipo = findViewById(R.id.img_logo_toolbar);
        imgCover = findViewById(R.id.imagem_detalhe_Id);
        txtLancamento = findViewById(R.id.txt_edition_month);
        btnMonthEdition = findViewById(R.id.btn_edicao_mes_id);
        btnLastEdition = findViewById(R.id.btn_edicao_anterior_id);
        btnContentOnLine = findViewById(R.id.btn_conteudo_on_id);
        progressBar = findViewById(R.id.progress_bar);

        ImageUtil.loadImage(Constantes.URL_BASE_COVER + imageCover, imgCover, progressBar, R.drawable.logo);
        txtLancamento.setText(dataLancamento);

        btnContentOnLine.setOnClickListener(v -> {
            Intent intent = new Intent(this, WebActivity.class);
            intent.putExtra("URL_ONLINE", contentOnLine);
            startActivity(intent);

        });

        btnMonthEdition.setOnClickListener(v -> {
            Intent intent = new Intent(this, LerEdicaoMesActivity.class);
            intent.putExtra("LOGOTIPO", logo);
            intent.putExtra("LANCAMENTO", dataLancamento);
            intent.putExtra("DESCRICAO", descricao);
            intent.putExtra("COVER", imageCover);
            intent.putExtra("PDF_FREE", pdfFree);
            intent.putExtra("URL_ONLINE", contentOnLine);
            intent.putExtra("URL", url);
            intent.putExtra("FILE_NAME", fileName);
            startActivity(intent);

        });

    }
}



//    private android.support.v7.widget.Toolbar toolbar;
//    private DrawerLayout drawerLayout;
//    private NavigationView navigationView;
//    private Button botaoLerEdicaoMes;


//        setContentView(R.layout.activity_apresentacao_revista);
//
//        toolbar = findViewById(R.id.toolbar_revista_id);
//        toolbar.inflateMenu(R.menu.menu_toolbar);
//
//        drawerLayout = findViewById(R.id.drawerLayoutId);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this
//                , drawerLayout
//                , toolbar
//                , R.string.open_drawer
//                , R.string.close_drawer);
//        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimary));
//        drawerLayout.addDrawerListener(toggle);
//
//        toggle.syncState();
//
//        navigationView = findViewById(R.id.navViewRevistaId);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        botaoLerEdicaoMes = findViewById(R.id.btn_edicao_mes_id);
//
//        botaoLerEdicaoMes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(ApresentacaoRevistaActivity.this, LerEdicaoMesActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });
//
//    }
//
//    //esse metodo faz com que, quando o botao de retorno do aparelho for pressionado, o drawer feche ao inves de sair do app
//    @Override
//    public void onBackPressed() {
//
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//
//            drawerLayout.closeDrawer(GravityCompat.START);
//
//        } else {
//
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//
//            case R.id.menu_edicao_atual: {
//                Toast.makeText(this, "Edição Atual", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case R.id.menu_edicoes_anteriores: {
//                Toast.makeText(this, "Edições Anteriores", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case R.id.menu_clube_assinante: {
//
//
//                Intent intent = new Intent(this, CupomDescontoActivity.class);
//                startActivity(intent);
//                //Toast.makeText(this, "Clube do Assinante", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case R.id.menu_atendimento: {
//                Toast.makeText(this, "Atendimento ao Leitor", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case R.id.menu_item1: {
//                Toast.makeText(this, "Item 1", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case R.id.menu_item2: {
//                Toast.makeText(this, "Item 2", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case R.id.menu_item3: {
//                Toast.makeText(this, "Item 3", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case R.id.menu_item4: {
//                Toast.makeText(this, "Item 4", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case R.id.menu_logoff: {
//                Toast.makeText(this, "Item 5", Toast.LENGTH_SHORT).show();
//                break;
//            }
//
//        }
//
//        drawerLayout.closeDrawer(GravityCompat.START);
//
//        return true;
//    }
//
////    @Override
////    public void onPointerCaptureChanged(boolean hasCapture) {
////
////    }

