package comdouglas_batista.github.cardapiodigital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AtualizaExclui extends Activity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private ImageView imagem;
    EditText numero, preco, ingrediente, nome ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza_exclui);

        Intent intent = getIntent();
        if(intent != null){
            Bundle dados = intent.getExtras();

            if(dados != null){
                String numeros = dados.getString("numero");
                String nomes = dados.getString("nome");
                String ingredientes = dados.getString("ingredientes");
                String precos = dados.getString("preco");

                nome = (EditText) findViewById(R.id.txenomePrato);
                ingrediente = (EditText) findViewById(R.id.txeNomeIngredientesPrato);
                preco = (EditText) findViewById(R.id.txePrecoPrato);
                numero = (EditText) findViewById(R.id.num);

                numero.setText(numeros);
                nome.setText(nomes);
                ingrediente.setText(ingredientes);
                preco.setText(precos);
            }
        }

        Button atualizarPrato = (Button) findViewById(R.id.atualizarPrato);
        Button excluirPrato = (Button) findViewById(R.id.excluirPrato);

        iniciaFirebase();

        atualizarPrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Modelo prato = new Modelo();
                prato.setNumeroPrato(numero.getText().toString());
                prato.setNomePrato(nome.getText().toString());
                prato.setPrecoPrato(preco.getText().toString());
                prato.setIngredientesPrato(ingrediente.getText().toString());
                //prato.setImagemPrato(imagem.getImage().toString());

                if(nome.getText().toString().isEmpty()||
                        preco.getText().toString().isEmpty()||numero.getText().toString().isEmpty()||ingrediente.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"Preencha todos os campos",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(),"Prato adicionado",Toast.LENGTH_LONG).show();
                    databaseReference.child("Prato").child(prato.getNumeroPrato()).setValue(prato);

                    finish();
                }
            }
        });


        excluirPrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Modelo prato = new Modelo();
                prato.setNumeroPrato(numero.getText().toString());
                prato.setNomePrato(nome.getText().toString());
                prato.setPrecoPrato(preco.getText().toString());
                prato.setIngredientesPrato(ingrediente.getText().toString());
                //prato.setImagemPrato(imagem.getImage().toString());

                prato.setNumeroPrato(numero.getText().toString());
                databaseReference.child("Prato").child(prato.getNumeroPrato()).removeValue();
                Toast.makeText(getApplicationContext(),"Prato excluido",Toast.LENGTH_LONG).show();
                finish();

            }
        });

    }

    private void iniciaFirebase() {

        FirebaseApp.initializeApp(AtualizaExclui.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}
