package comdouglas_batista.github.cardapiodigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    private Button btnUsuario;
    private Button btnAdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnUsuario = (Button) findViewById(R.id.btnUsuario);
        btnAdm = (Button) findViewById(R.id.btnAdmin);

        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirmenu = new Intent(Inicio.this, Cliente.class);
                startActivity(abrirmenu);
            }
        });

        btnAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abriradm = new Intent(Inicio.this, Administrador.class);
                startActivity(abriradm);
            }
        });

    }
}
