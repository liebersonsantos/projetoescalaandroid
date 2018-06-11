package br.com.escala.app.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import br.com.escala.app.R;

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
