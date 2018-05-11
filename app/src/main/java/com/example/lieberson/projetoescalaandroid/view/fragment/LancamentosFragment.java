package com.example.lieberson.projetoescalaandroid.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lieberson.projetoescalaandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LancamentosFragment extends Fragment {


    public LancamentosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lancamentos, container, false);
    }

}
