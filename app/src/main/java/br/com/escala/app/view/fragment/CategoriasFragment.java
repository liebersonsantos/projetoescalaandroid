package br.com.escala.app.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.escala.app.R;
import br.com.escala.app.adapters.AdapterCategories;
import br.com.escala.app.model.CategoryResponse;
import br.com.escala.app.model.MagazineRespose;
import br.com.escala.app.model.Revista;
import br.com.escala.app.network.RestClient;
import br.com.escala.app.view.ContentSelectedCategoryActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriasFragment extends Fragment {

    @BindView(R.id.recycler_fragment_categorias)
    RecyclerView recyclerViewCat;

    private AdapterCategories adapterCategories;

    public CategoriasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categorias, container, false);

        ButterKnife.bind(this, view);

        adapterCategories = new AdapterCategories();

        recyclerViewCat.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCat.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCat.setAdapter(adapterCategories);

        RestClient.getInstance().getAllCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {

                if (response.isSuccessful() && response.body() != null){

                    adapterCategories.setCategoriesList(response.body().getCategories());
                }else {

                    System.out.println("ERRO ao carregar as imagens");
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

                Toast.makeText(getContext(), "Erro ao carregar Dados", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
