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
import br.com.escala.app.adapters.RecyclerViewLancamentosAdapter;
import br.com.escala.app.model.Revista;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LancamentosFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewLancamentosAdapter adapter;
    private List<Revista> revistaList;


    public LancamentosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lancamentos, container, false);

        revistaList = gerarDados(10);

        recyclerView = view.findViewById(R.id.recyclerView_lanc_id);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerViewLancamentosAdapter(this, revistaList);
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
            revista1.setImage("http://cleooficial.com/wp-content/uploads/2018/02/capa-revista-marie-claire-julho-2016-cleo-pires-bancas.jpg");
            revista1.setUrlPdf("/scielobooks/38m/pdf/santos-9788523209087.pdf");

            revistas.add(revista1);

        }

        return revistas;
    }

}
