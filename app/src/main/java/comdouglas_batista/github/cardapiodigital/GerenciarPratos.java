package comdouglas_batista.github.cardapiodigital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GerenciarPratos extends Activity {

    private Button atualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_pratos);

        atualizar = (Button) findViewById(R.id.btnAtua);

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent entPrato = new Intent(GerenciarPratos.this, AtualizarExcluir.class);
                startActivity(entPrato);
            }
        });
    }
}
