package br.com.escala.app.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.escala.app.R;
import br.com.escala.app.helper.Constantes;
import br.com.escala.app.helper.ImageUtil;
import br.com.escala.app.model.MagazineRespose;
import br.com.escala.app.model.Revista;
import br.com.escala.app.network.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EdicaoAtualActivity extends AppCompatActivity {

    private ImageView imgEdicaoAtual;
    private ProgressBar progressBarEdAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_atual);


//        ImageUtil.loadImage(Constantes.URL_BASE_COVER + revista.getImage(),imgEdicaoAtual, progressBarEdAtual, R.drawable.logo);

    }
}
