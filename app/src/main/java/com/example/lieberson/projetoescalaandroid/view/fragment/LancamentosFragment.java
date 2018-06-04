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

        recyclerView = view.findViewById(R.id.recyclerviewlancamento_id);

        revistasList = new ArrayList<>();
        adapter = new RecyclerViewLancamentosAdapter(this, revistasList);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }


}
