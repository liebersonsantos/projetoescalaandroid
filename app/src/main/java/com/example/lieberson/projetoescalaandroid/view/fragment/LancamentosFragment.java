package com.example.lieberson.projetoescalaandroid.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lieberson.projetoescalaandroid.R;
import com.example.lieberson.projetoescalaandroid.adapters.AdapterLogin;
import com.example.lieberson.projetoescalaandroid.adapters.RecyclerViewLancamentosAdapter;
import com.example.lieberson.projetoescalaandroid.model.Revistas;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LancamentosFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewLancamentosAdapter adapter;
    private List<Revistas> revistasList;


    public LancamentosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lancamentos, container, false);

        revistasList = gerarDados(10);

        recyclerView = view.findViewById(R.id.recyclerView_lanc_id);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerViewLancamentosAdapter(this, revistasList);
        recyclerView.setAdapter(adapter);



        return view;
    }

    private List<Revistas> gerarDados(int quant) {

        List<Revistas> revistas = new ArrayList<>();

        for (int i = 0; i < quant; i++) {

            Revistas revistas1 = new Revistas();
            revistas1.setId(i);
            revistas1.setCategoria("categoria " + i);
            revistas1.setDataLancamento("00/00/00");
            revistas1.setNomeRevista("nome " + i);
            revistas1.setDescricao("descricao " + i);
            revistas1.setUrl_revista("http://cleooficial.com/wp-content/uploads/2018/02/capa-revista-marie-claire-julho-2016-cleo-pires-bancas.jpg");

            revistas.add(revistas1);

        }

        return revistas;
    }


}
