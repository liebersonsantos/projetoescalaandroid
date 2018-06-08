package com.example.lieberson.projetoescalaandroid.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lieberson.projetoescalaandroid.R;
import com.example.lieberson.projetoescalaandroid.adapters.AdapterBanca;
import com.example.lieberson.projetoescalaandroid.adapters.RecyclerViewLancamentosAdapter;
import com.example.lieberson.projetoescalaandroid.model.Revistas;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MinhaBancaFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterBanca adapter;
    private List<Revistas> revistasList;



    public MinhaBancaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_minha_banca, container, false);

        revistasList = gerarDados(5);

        recyclerView = view.findViewById(R.id.recyclerView_banca_id);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new AdapterBanca(this, revistasList);
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
            revistas1.setUrl_revista("https://opiniaorh.files.wordpress.com/2013/03/revista-voce-sa.jpg");

            revistas.add(revistas1);

        }

        return revistas;
    }


}
