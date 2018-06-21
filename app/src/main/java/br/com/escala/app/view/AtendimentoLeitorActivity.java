package br.com.escala.app.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.com.escala.app.R;

public class AtendimentoLeitorActivity extends BaseActivity {

    private RadioButton radioButtonChosen;
    private RadioGroup radioGroup;
    private Button btnSendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerView(R.layout.activity_atendimento_leitor);

        radioGroup = findViewById(R.id.radio_group_id);
        btnSendData = findViewById(R.id.btn_enviar_dados);

        btnSendData.setOnClickListener(v -> {

            int idRadioButtonChosen = radioGroup.getCheckedRadioButtonId();

            if (idRadioButtonChosen > 0){
                radioButtonChosen = findViewById(idRadioButtonChosen);

                Toast.makeText(AtendimentoLeitorActivity.this, radioButtonChosen.getText(), Toast.LENGTH_SHORT).show();
            }

        });

    }
}
