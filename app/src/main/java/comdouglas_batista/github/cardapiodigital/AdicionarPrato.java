package comdouglas_batista.github.cardapiodigital;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class AdicionarPrato extends Activity {

    private static int IMAGEM_PEGA = 1;
    private Uri imagem;
    private ImageView imagemV;

    EditText numero;
    EditText nome;
    EditText ingrediente;
    EditText preco;
    EditText nomeFoto;

    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_prato);

        imagemV = (ImageView) findViewById(R.id.imagemPrato);
        numero = (EditText) findViewById(R.id.numPrato);
        nome = (EditText) findViewById(R.id.nomePrato);
        ingrediente = (EditText) findViewById(R.id.ingredientesPrato);
        preco = (EditText) findViewById(R.id.precoPrato);
        Button pegaimagem = (Button) findViewById(R.id.SelImagem);
        Button addPrato = (Button) findViewById(R.id.btnAddPrato);
        nomeFoto = (EditText) findViewById(R.id.gambiarra1);

        iniciarFirebase();

        addPrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(numero.getText().toString().isEmpty()||nome.getText().toString().isEmpty()||
                        preco.getText().toString().isEmpty()||ingrediente.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();

                }else if (imagem == null){

                    Toast.makeText(getApplicationContext(),"Selecione uma imagem",Toast.LENGTH_LONG).show();

                } else {
                    //storageReference;
                    storageReference = FirebaseStorage.getInstance().getReference().child(nome.getText()+".jpg");
                    storageReference.putFile(imagem)
                            .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()){
                                throw Objects.requireNonNull(task.getException());
                            }
                            return storageReference.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()){
                                Uri downUri = task.getResult();
                                assert downUri != null;
                                Log.d("url", "onComplete: Url: "+ downUri.toString());
                                Toast.makeText(getApplicationContext(),"Sucesso",Toast.LENGTH_LONG).show();
                                nomeFoto.setText(downUri.toString());

                                Modelo prato = new Modelo();
                                prato.setNumeroPrato(numero.getText().toString());
                                prato.setNomePrato(nome.getText().toString());
                                prato.setPrecoPrato(preco.getText().toString());
                                prato.setIngredientesPrato(ingrediente.getText().toString());
                                prato.setImagemURL(nomeFoto.getText().toString());
                                databaseReference.child("Prato").child(prato.getNumeroPrato()).setValue(prato);
                                limparCampos();
                            }
                        }
                    });
                }
            }

            private void limparCampos() {
                numero.setText("");
                nome.setText("");
                preco.setText("");
                ingrediente.setText("");
                imagemV.setImageDrawable(null);
            }
        });

        //pega imagem na galeria
        pegaimagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent pegaImagem = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pegaImagem, IMAGEM_PEGA);
            }
        });
    }

    //coloca a imagem no image view
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== IMAGEM_PEGA  && resultCode == RESULT_OK) {
            imagem = data.getData();
            Picasso.get().load(imagem).into(imagemV);
        }
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(AdicionarPrato.this);

        databaseReference = FirebaseDatabase.getInstance().getReference();
    }
}
