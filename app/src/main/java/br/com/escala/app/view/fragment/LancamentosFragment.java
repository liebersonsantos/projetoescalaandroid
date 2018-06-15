package br.com.escala.app.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.com.escala.app.R;
import br.com.escala.app.adapters.RecyclerViewLancamentosAdapter;
import br.com.escala.app.model.Login;
import br.com.escala.app.model.MagazineRespose;
import br.com.escala.app.model.Revista;
import br.com.escala.app.network.RestClient;
import br.com.escala.app.view.LoginActivity;
import br.com.escala.app.view.PdfViewActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LancamentosFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewLancamentosAdapter adapter;
    private List<Revista> revistaList;
    private LoginActivity loginActivity;


    public LancamentosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lancamentos, container, false);

//        revistaList = gerarDados(10);

        recyclerView = view.findViewById(R.id.recyclerView_lanc_id);

        adapter = new RecyclerViewLancamentosAdapter();

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        getWsMagazineOn();

        return view;
    }

    private void getWsMagazineOn() {

        RestClient.getInstance().magazines().enqueue(new Callback<MagazineRespose>() {
            @Override
            public void onResponse(Call<MagazineRespose> call, Response<MagazineRespose> response) {

                if (response.isSuccessful() && response.body() != null){

                    adapter.setRevistaList(response.body().getRevistas());
                }else {

                    System.out.println("ERRO ao carregar as imagens");
                }

            }

            @Override
            public void onFailure(Call<MagazineRespose> call, Throwable t) {

                Toast.makeText(getContext(), "Erro ao carregar Dados", Toast.LENGTH_SHORT).show();
            }
        });

    }

}




//    private List<Revista> gerarDados(int quant) {
//
//        List<Revista> revistas = new ArrayList<>();
//
//        for (int i = 0; i < quant; i++) {
//
//            Revista revista1 = new Revista();
//            revista1.setId(i);
//            revista1.setCategoria_id("categoria " + i);
//            revista1.setDataLancamento("00/00/00");
//            revista1.setNomeRevista("nome " + i);
//            revista1.setDescricao("descricao " + i);
//            revista1.setImage("http://cleooficial.com/wp-content/uploads/2018/02/capa-revista-marie-claire-julho-2016-cleo-pires-bancas.jpg");
//            revista1.setUrlPdfFree("/scielobooks/38m/pdf/santos-9788523209087.pdf");
//
//            revistas.add(revista1);
//
//        }
//
//        return revistas;
//    }