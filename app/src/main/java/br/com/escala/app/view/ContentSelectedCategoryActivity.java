package br.com.escala.app.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.com.escala.app.R;
import br.com.escala.app.adapters.AdapterContentSelectedCategory;
import br.com.escala.app.model.MagazineRespose;
import br.com.escala.app.network.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentSelectedCategoryActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private AdapterContentSelectedCategory adapterContentSelectedCategory;
    private String idCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerView(R.layout.activity_content_selected_category);

        Bundle extra = getIntent().getExtras();

        if (extra != null){
            idCategory = getIntent().getStringExtra("IDCATEGORY");
        }

        initViews();
        settingsAdapter();


        RestClient.getInstance().selectCategory(idCategory).enqueue(new Callback<MagazineRespose>() {
            @Override
            public void onResponse(Call<MagazineRespose> call, Response<MagazineRespose> response) {

                if (response.isSuccessful() && response.body() != null){

                    adapterContentSelectedCategory.setRevistaList(response.body().getRevistas());
                }else {

                    System.out.println("ERRO ao carregar as imagens");
                }

            }

            @Override
            public void onFailure(Call<MagazineRespose> call, Throwable t) {

            }
        });

    }

    private void settingsAdapter() {

        adapterContentSelectedCategory = new AdapterContentSelectedCategory();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterContentSelectedCategory);

    }

    private void initViews() {

        recyclerView = findViewById(R.id.recyclerView_content_category);
    }
}
