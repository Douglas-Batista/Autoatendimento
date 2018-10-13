package comdouglas_batista.github.cardapiodigital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Administrador extends Activity {

    private Button fila;
    private Button mesa;
    private Button pratos;
    private Button adicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        fila = (Button) findViewById(R.id.btnFila);
        mesa = (Button) findViewById(R.id.btnMesa);
        pratos = (Button) findViewById(R.id.btnPratos);
        adicionar = (Button) findViewById(R.id.btnAdicionarPra);

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
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adiPrato = new Intent(Administrador.this, AdicionarPrato.class);
                startActivity(adiPrato);
            }
        });
    }
}
