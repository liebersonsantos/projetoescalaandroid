package com.example.lieberson.projetoescalaandroid.view;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lieberson.projetoescalaandroid.R;
import com.example.lieberson.projetoescalaandroid.view.fragment.CategoriasFragment;
import com.example.lieberson.projetoescalaandroid.view.fragment.LancamentosFragment;
import com.example.lieberson.projetoescalaandroid.view.fragment.MinhaBancaFragment;
import com.example.lieberson.projetoescalaandroid.view.fragment.TitulosFragment;

public class ApresentacaoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private static TextView textFragmentLancamentos;
    private static TextView textFragmentMinhaBanca;
    private static TextView textFragmentCategorias;
    private static TextView textFragmentTitulos;


    //Fragments
    private static LancamentosFragment lancamentosFragment;
    private static MinhaBancaFragment minhaBancaFragment;
    private static CategoriasFragment categoriasFragment;
    private static TitulosFragment titulosFragment;
    private static LinearLayout linearLayout;

    private static int indice = 1;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        toolbar = findViewById(R.id.toolbarId);
        textFragmentLancamentos = findViewById(R.id.text_lancamentos);
        textFragmentMinhaBanca = findViewById(R.id.text_minha_banca);
        textFragmentCategorias = findViewById(R.id.text_categorias);
        textFragmentTitulos = findViewById(R.id.text_titulos);
        linearLayout = findViewById(R.id.linear_view_id);

        toolbar.inflateMenu(R.menu.menu_toolbar);

        //criando o DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayoutId);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this
                , drawerLayout
                , toolbar
                , R.string.open_drawer
                , R.string.close_drawer);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = findViewById(R.id.navViewId);
        navigationView.setNavigationItemSelectedListener(this);

        lancamentosFragment = new LancamentosFragment();
        minhaBancaFragment = new MinhaBancaFragment();
        categoriasFragment = new CategoriasFragment();
        titulosFragment = new TitulosFragment();


        setupFragment(textFragmentLancamentos, 1);
        setupFragment(textFragmentMinhaBanca, 2);
        setupFragment(textFragmentCategorias, 3);
        setupFragment(textFragmentTitulos, 4);

        setFragment(indice);
    }

    private void setupFragment(final TextView item, final int i) {

        item.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                switch (i) {
                    case 1:
                        if (indice != 1) {
                            indice = 1;
                            setFragment(i);
                        }
                        break;
                    case 2:
                        if (indice != 2) {
                            indice = 2;
                            setFragment(i);
                        }
                        break;
                    case 3:
                        if (indice != 3) {
                            indice = 3;

                            setFragment(i);
                        }
                        break;
                    case 4:
                        if (indice != 4) {
                            indice = 4;

                            setFragment(i);
                        }

                        break;
                }

            }
        });
    }

    //esse metodo faz com que, quando o botao de retorno do aparelho for pressionado, o drawer feche ao inves de sair do app
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setFragment(int indice) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (indice) {

            case 1:
                ft.replace(R.id.linear_frag, lancamentosFragment, "Tela dos Lançamentos");
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.linear_frag, minhaBancaFragment, "Minha Banca");
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.linear_frag, categoriasFragment, "Categorias");
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.linear_frag, titulosFragment, "Titulos");
                ft.commit();
                break;
        }

        setIndiceMenu(indice, linearLayout);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setIndiceMenu(int indice, LinearLayout linearLayout) {

        for (int i = 0; i < linearLayout.getChildCount(); i++) {

            View view = linearLayout.getChildAt(i);

            if (i < 4) {

                view.setBackground(ApresentacaoActivity.this.getResources().getDrawable(R.drawable.background_blue_apresentacao)); // Or whatever you want to do with the view.
                textFragmentLancamentos.setTextColor(getResources().getColor(R.color.colorPrimary));
                textFragmentMinhaBanca.setTextColor(getResources().getColor(R.color.colorPrimary));
                textFragmentCategorias.setTextColor(getResources().getColor(R.color.colorPrimary));
                textFragmentTitulos.setTextColor(getResources().getColor(R.color.colorPrimary));
            } else {

                textFragmentTitulos.setBackgroundColor(getResources().getColor(R.color.colorToolbar));
                textFragmentTitulos.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }

        switch (indice) {
            case 1:
                textFragmentLancamentos.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textFragmentLancamentos.setTextColor(getResources().getColor(R.color.colorToolbar));
                break;

            case 2:
                textFragmentMinhaBanca.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textFragmentMinhaBanca.setTextColor(getResources().getColor(R.color.colorToolbar));
                break;

            case 3:
                textFragmentCategorias.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textFragmentCategorias.setTextColor(getResources().getColor(R.color.colorToolbar));
                break;

            case 4:
                textFragmentTitulos.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textFragmentTitulos.setTextColor(getResources().getColor(R.color.colorToolbar));
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_edicao_atual: {
                Toast.makeText(this, "Edição Atual", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_edicoes_anteriores: {
                Toast.makeText(this, "Edições Anteriores", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_clube_assinante: {
                Toast.makeText(this, "Clube do Assinante", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_atendimento: {
                Toast.makeText(this, "Atendimento ao Leitor", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_item1: {
                Toast.makeText(this, "Item 1", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_item2: {
                Toast.makeText(this, "Item 2", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_item3: {
                Toast.makeText(this, "Item 3", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_item4: {
                Toast.makeText(this, "Item 4", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_logoff: {
                Toast.makeText(this, "Item 5", Toast.LENGTH_SHORT).show();
                break;
            }

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
