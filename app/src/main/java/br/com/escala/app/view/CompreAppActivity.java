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
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.escala.app.R;

public class CompreAppActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ImageView botaoAssine, botaoCompre;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compre_app);

        botaoAssine = findViewById(R.id.image_botao_assine_id);
        botaoCompre = findViewById(R.id.image_botao_compre_id);


        toolbar = findViewById(R.id.toolbarId);
        toolbar.inflateMenu(R.menu.menu_toolbar);
    }
}
