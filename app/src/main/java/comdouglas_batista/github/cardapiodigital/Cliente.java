package comdouglas_batista.github.cardapiodigital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cliente extends Activity {

    private Button exemplo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        exemplo = (Button) findViewById(R.id.btnExemplo);

        exemplo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent entPrato = new Intent(Cliente.this, Prato.class);
                startActivity(entPrato);
            }
        });
    }
}
