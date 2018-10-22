package comdouglas_batista.github.cardapiodigital;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AdicionarPrato extends Activity {

    private static int IMAGEM_PEGA = 1;
    private ImageView imagem;
    EditText numero, nome, ingrediente,preco;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_prato);

        imagem = (ImageView) findViewById(R.id.imagemPrato);
        Button pegaimagem = (Button) findViewById(R.id.SelImagem);
        numero = (EditText) findViewById(R.id.numPrato);
        nome = (EditText) findViewById(R.id.nomePrato);
        ingrediente = (EditText) findViewById(R.id.ingredientesPrato);
        preco = (EditText) findViewById(R.id.precoPrato);
        Button addPrato = (Button) findViewById(R.id.btnAddPrato);

        iniciarFirebase();

        addPrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelo prato = new Modelo();
                prato.setNumeroPrato(numero.getText().toString());
                prato.setNomePrato(nome.getText().toString());
                prato.setPrecoPrato(preco.getText().toString());
                prato.setIngrdientesPrato(ingrediente.getText().toString());
                //prato.setImagemPrato(imagem.getImage().toString());

                if(numero.getText().toString().isEmpty()||nome.getText().toString().isEmpty()||
                        preco.getText().toString().isEmpty()||ingrediente.getText().toString().isEmpty()){
<<<<<<< HEAD
=======
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> adicionar prato

                    Toast.makeText(getApplicationContext(),"Preencha todos os campos",Toast.LENGTH_LONG).show();

                } else {

                    databaseReference.child("Prato").child(prato.getNumeroPrato()).setValue(prato);
                    limparCampos();
                }
            }

            private void limparCampos() {
                numero.setText("");
                nome.setText("");
                preco.setText("");
                ingrediente.setText("");
            }
        });

<<<<<<< HEAD

=======

=======
=======
>>>>>>> tela adicionar prato pronta

                    Toast.makeText(getApplicationContext(),"Preencha todos os campos",Toast.LENGTH_LONG).show();

                } else {

                    databaseReference.child("Prato").child(prato.getNumeroPrato()).setValue(prato);
                    limparCampos();
                }
            }

            private void limparCampos() {
                numero.setText("");
                nome.setText("");
                preco.setText("");
                ingrediente.setText("");
            }
        });


<<<<<<< HEAD
>>>>>>> 2b1acf2f85c296a272f4d81d93450194c014926e
=======
>>>>>>> tela adicionar prato pronta
>>>>>>> adicionar prato
        //pega imagem na galeria
        pegaimagem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, IMAGEM_PEGA);
            }
        });
    }

    //coloca a imagem no image view
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== IMAGEM_PEGA  && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            imagem.setImageURI(fullPhotoUri);
        }
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(AdicionarPrato.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
<<<<<<< HEAD



=======
>>>>>>> 2b1acf2f85c296a272f4d81d93450194c014926e
=======



>>>>>>> tela adicionar prato pronta
>>>>>>> adicionar prato
}

