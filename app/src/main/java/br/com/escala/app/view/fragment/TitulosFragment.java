package br.com.escala.app.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.escala.app.R;
import br.com.escala.app.adapters.AdapterTitle;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TitulosFragment extends Fragment {

    @BindView(R.id.recycler_fragment_titulos)
    RecyclerView recyclerViewTitle;

    private AdapterTitle adapterTitle;

    public TitulosFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_titulos, container, false);
        ButterKnife.bind(this, view);

        adapterTitle = new AdapterTitle();

        recyclerViewTitle.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewTitle.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTitle.setAdapter(adapterTitle);


        return view;
    }

}
