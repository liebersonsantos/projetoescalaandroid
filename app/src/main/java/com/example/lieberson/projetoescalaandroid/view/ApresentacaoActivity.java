package com.example.lieberson.projetoescalaandroid.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.example.lieberson.projetoescalaandroid.R;

public class ApresentacaoActivity extends BaseActivity{


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerView(R.layout.activity_apresentacao);

        setFragmentsPageAdapter(new SectionsPageAdapter(getSupportFragmentManager()));

        setTabsPage();

    }

}
