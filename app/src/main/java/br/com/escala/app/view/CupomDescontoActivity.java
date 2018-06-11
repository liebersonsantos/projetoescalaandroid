package br.com.escala.app.view;

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
import android.widget.Toast;

import br.com.escala.app.R;

public class CupomDescontoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cumpom_desconto);

        toolbar = findViewById(R.id.toolbar_clube_assinante_Id);
        toolbar.inflateMenu(R.menu.menu_toolbar);

        drawerLayout = findViewById(R.id.drawerLayoutId);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this
                , drawerLayout
                , toolbar
                , R.string.open_drawer
                , R.string.close_drawer);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = findViewById(R.id.navViewCupomId);
        navigationView.setNavigationItemSelectedListener(this);

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
