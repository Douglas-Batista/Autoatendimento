package comdouglas_batista.github.cardapiodigital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Administrador extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        Button fila = (Button) findViewById(R.id.btnFila);
        Button mesa = (Button) findViewById(R.id.btnMesa);
        Button pratos = (Button) findViewById(R.id.btnEditPratos);
        Button addpratos = (Button) findViewById(R.id.btnAddPrato);


        fila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filaPedidos = new Intent(Administrador.this, FilaDePedidos.class);
                startActivity(filaPedidos);
            }
        });

        mesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent genMesa = new Intent(Administrador.this, AdministrarMesas.class);
                startActivity(genMesa);
            }
        });

        pratos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gemPratos = new Intent(Administrador.this, GerenciarPratos.class);
                startActivity(gemPratos);
            }
        });

        addpratos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adicionarPrato = new Intent(Administrador.this, AdicionarPrato.class);
                startActivity(adicionarPrato);

            }
        });
    }
}
