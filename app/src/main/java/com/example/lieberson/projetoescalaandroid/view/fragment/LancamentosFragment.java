package com.example.lieberson.projetoescalaandroid.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lieberson.projetoescalaandroid.R;
import com.example.lieberson.projetoescalaandroid.view.ApresentacaoActivity;
import com.example.lieberson.projetoescalaandroid.view.ApresentacaoRevistaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LancamentosFragment extends Fragment {

    private ImageView imagemCorpo;


    public LancamentosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lancamentos, container, false);

        imagemCorpo = view.findViewById(R.id.imageView15);

        imagemCorpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ApresentacaoRevistaActivity.class);
                startActivity(intent);

            }
        });


        return view;

    }

}
