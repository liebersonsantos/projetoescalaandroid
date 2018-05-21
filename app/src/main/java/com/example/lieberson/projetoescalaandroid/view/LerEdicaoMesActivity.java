package com.example.lieberson.projetoescalaandroid.view;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lieberson.projetoescalaandroid.R;

public class LerEdicaoMesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private android.support.v7.widget.Toolbar toolbar;
    private android.support.v4.widget.DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button botaoLerEdicao;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ler_edicao_mes);



        toolbar = findViewById(R.id.toolbar_revista_id);
        toolbar.inflateMenu(R.menu.menu_toolbar);

        drawerLayout = findViewById(R.id.drawerLayoutId);

        android.support.v7.app.ActionBarDrawerToggle toggle = new android.support.v7.app.ActionBarDrawerToggle(this
                , drawerLayout
                , toolbar
                , R.string.open_drawer
                , R.string.close_drawer);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = findViewById(R.id.navViewEdicaoMesId);
        navigationView.setNavigationItemSelectedListener(this);

        botaoLerEdicao = findViewById(R.id.btn_ler_edicao_mes_detalhe_id);

        botaoLerEdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LerEdicaoMesActivity.this, TelaEdicaoMesActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

     //esse metodo faz com que, quando o botao de retorno do aparelho for pressionado, o drawer feche ao inves de sair do app
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(android.support.v4.view.GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();
        }
    }

     @Override
    public boolean onNavigationItemSelected(@android.support.annotation.NonNull MenuItem item) {

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
                Intent intent = new Intent(this, CupomDescontoActivity.class);
                startActivity(intent);
                //Toast.makeText(this, "Clube do Assinante", Toast.LENGTH_SHORT).show();
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
