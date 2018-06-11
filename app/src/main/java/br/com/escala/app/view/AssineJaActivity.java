package br.com.escala.app.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.escala.app.R;

public class AssineJaActivity extends AppCompatActivity {

    private Button botaoAssineJa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assine_ja);

        botaoAssineJa = findViewById(R.id.btn_assine_clube_id);

        botaoAssineJa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AssineJaActivity.this, CompreAppActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }
}
