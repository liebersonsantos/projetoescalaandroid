package br.com.escala.app.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.com.escala.app.R;
import br.com.escala.app.model.MagazineRespose;
import br.com.escala.app.model.Revista;
import br.com.escala.app.network.RestClient;
import br.com.escala.app.view.ContentSelectedCategoryActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriasFragment extends Fragment {

    private ImageView imgArqDec;

    public CategoriasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categorias, container, false);

        imgArqDec = view.findViewById(R.id.image_arq_dec);

        imgArqDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), ContentSelectedCategoryActivity.class);
                intent.putExtra("IDCATEGORY", "1");
                startActivity(intent);

            }
        });

        return view;
    }

}
