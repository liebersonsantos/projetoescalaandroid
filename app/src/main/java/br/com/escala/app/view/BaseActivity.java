package br.com.escala.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import br.com.escala.app.R;
import br.com.escala.app.helper.Preferencias;
import br.com.escala.app.model.MagazineContentsRelated;
import br.com.escala.app.model.Revista;
import io.reactivex.disposables.CompositeDisposable;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentStatePagerAdapter framentStatePageAdapter;
    private android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    protected CompositeDisposable disposable = new CompositeDisposable(); //guarda os caras que encadeiam as stream do rx


    private FrameLayout container;

    private Revista revista;

    private ViewPager mViewPager;

    private int tabSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        container = findViewById(R.id.container);



//        Bundle extra = getIntent().getExtras();
//        if( extra != null ){
//            revista = getIntent().getExtras().getParcelable("REVISTA");
//        }else {
//            Log.i("TAG", "onCreateBUNDLE: " + revista);
//        }

    }

    public void setTabsPage() {

        mViewPager = (ViewPager) findViewById(R.id.container_viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        if (mViewPager != null) {

            mViewPager.setAdapter(framentStatePageAdapter);
            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }

        if (tabLayout != null) {

            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
            tabLayout.setVisibility(View.VISIBLE);
        }

    }

    public void setContainerView(int layout) {

        View view = getLayoutInflater().inflate(layout, null);
        container.addView(view);
        setNavigationDrawer();

    }

    private void setNavigationDrawer() { //--> --> --> ctrl + alt + m cria metodo

        toolbar = findViewById(R.id.toolbarId);
        drawerLayout = findViewById(R.id.drawerLayoutId);


        if (toolbar != null) {

            toolbar.inflateMenu(R.menu.menu_toolbar);
            setSupportActionBar(toolbar);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this
                    , drawerLayout
                    , toolbar
                    , R.string.open_drawer
                    , R.string.close_drawer);
            toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimary));
            drawerLayout.addDrawerListener(toggle);

            toggle.syncState();
        }

        navigationView = findViewById(R.id.navViewId);
        if (navigationView != null){

            Menu menu = navigationView.getMenu();
//
//            List<MagazineContentsRelated> magazineContentsRelateds = revista.getMagazineContentsRelated();
//
//            for (int i = 0; i < magazineContentsRelateds.size(); i++) {
//
//                menu.findItem(R.id.menu_item1).setTitle(magazineContentsRelateds.get(i).describeContents());
//
//            }

            menu.findItem(R.id.menu_item1).setTitle("teste1");
            menu.findItem(R.id.menu_item2).setTitle("teste2");
            menu.findItem(R.id.menu_item3).setTitle("teste3");
            menu.findItem(R.id.menu_item4).setTitle("teste4");

            navigationView.setNavigationItemSelectedListener(this);
        }
    }


    //esse metodo faz com que, quando o botao de retorno do aparelho for pressionado, o drawer feche ao inves de sair do app
    @Override
    public void onBackPressed() {

        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_edicao_atual: {
                Intent intent = new Intent(this, EdicaoAtualActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_edicoes_anteriores: {
                Toast.makeText(this, "Edições Anteriores", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_clube_assinante: {


                Intent intent = new Intent(this, CupomDescontoActivity.class);
                startActivity(intent);
                //Toast.makeText(this, "Clube do Assinante", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_atendimento: {
                Intent intent = new Intent(this, AtendimentoLeitorActivity.class);
                startActivity(intent);
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
                Preferencias preferencias = new Preferencias(this);
                preferencias.clearPreferences();

                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            }

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    public void setFragmentsPageAdapter(FragmentStatePagerAdapter adapter){

        this.framentStatePageAdapter = adapter;
    }

    @Override
    protected void onDestroy() {
        if (disposable != null){
            disposable.dispose();
        }

        super.onDestroy();
    }

    public void toExchangeMenu(){



    }


}
