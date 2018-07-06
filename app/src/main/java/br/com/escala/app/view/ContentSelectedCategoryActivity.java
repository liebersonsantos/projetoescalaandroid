package br.com.escala.app.view;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import br.com.escala.app.R;
import br.com.escala.app.adapters.AdapterContentSelectedCategory;
import br.com.escala.app.model.MagazineCategoryResponse;
import br.com.escala.app.network.RestClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentSelectedCategoryActivity extends BaseActivity {

    @BindView(R.id.recyclerView_content_category)
    RecyclerView recyclerView;

    private AdapterContentSelectedCategory adapterContentSelectedCategory;
    private String idCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerView(R.layout.activity_content_selected_category);

        setDrawerVisibility(true);

        Bundle extra = getIntent().getExtras();

        if (extra != null){
            idCategory = getIntent().getStringExtra("ID_CATEGORY");
        }

        ButterKnife.bind(this);

        settingsAdapter();
        getCategorySelected();

    }

    private void getCategorySelected() {
        RestClient.getInstanceCategory().selectCategory(idCategory).enqueue(new Callback<MagazineCategoryResponse>() {
            @Override
            public void onResponse(Call<MagazineCategoryResponse> call, Response<MagazineCategoryResponse> response) {

                if (response.isSuccessful() && response.body() != null){

                    Log.i("TAG", "onResponseGetCategorySelected: " + response.body().getResultado());

                    adapterContentSelectedCategory.setRevistaList(response.body().getRevistas());
                }else {

                    Log.i("TAG", "onResponse: Erro" + response.body().getResultado());
                }

            }

            @Override
            public void onFailure(Call<MagazineCategoryResponse> call, Throwable t) {

                Log.i("TAG", "onFailure: " + t.getMessage());

                Toast.makeText(ContentSelectedCategoryActivity.this, "Verificar Retrofit", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void settingsAdapter() {

        adapterContentSelectedCategory = new AdapterContentSelectedCategory();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(adapterContentSelectedCategory);

    }

}
