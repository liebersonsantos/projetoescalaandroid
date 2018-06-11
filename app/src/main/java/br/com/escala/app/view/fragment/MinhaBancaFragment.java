package br.com.escala.app.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.escala.app.R;
import br.com.escala.app.adapters.AdapterBanca;
import br.com.escala.app.model.Revista;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MinhaBancaFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterBanca adapter;
    private List<Revista> revistaList;



    public MinhaBancaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_minha_banca, container, false);

        revistaList = gerarDados(5);

        recyclerView = view.findViewById(R.id.recyclerView_banca_id);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new AdapterBanca(this, revistaList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Revista> gerarDados(int quant) {

        List<Revista> revistas = new ArrayList<>();

        for (int i = 0; i < quant; i++) {

            Revista revista1 = new Revista();
            revista1.setId(i);
            revista1.setCategoria("categoria " + i);
            revista1.setDataLancamento("00/00/00");
            revista1.setNomeRevista("nome " + i);
            revista1.setDescricao("descricao " + i);
            revista1.setImage("https://opiniaorh.files.wordpress.com/2013/03/revista-voce-sa.jpg");
            revista1.setUrlPdf("/scielobooks/38m/pdf/santos-9788523209087.pdf");

            revistas.add(revista1);

        }

        return revistas;
    }


}
