package comdouglas_batista.github.cardapiodigital;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class AtualizaExclui extends Activity {

    private static int IMAGEM_PEGA = 1;
    private ImageView atualizaImagemPrato;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    Uri imagemP;
    TextView image;
    EditText numero, preco, ingrediente, nome ;
    Button atualizaImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza_exclui);

        atualizaImagem = (Button) findViewById(R.id.atualizaImagem);

        Intent intent = getIntent();
        if(intent != null){
            Bundle dados = intent.getExtras();

            if(dados != null){
                String numeros = dados.getString("numero");
                String nomes = dados.getString("nome");
                String ingredientes = dados.getString("ingredientes");
                String precos = dados.getString("preco");
                String imagems = dados.getString("imagem");

                image = (TextView) findViewById(R.id.gabiarra2);
                nome = (EditText) findViewById(R.id.txenomePrato);
                ingrediente = (EditText) findViewById(R.id.txeNomeIngredientesPrato);
                preco = (EditText) findViewById(R.id.txePrecoPrato);
                numero = (EditText) findViewById(R.id.num);
                atualizaImagemPrato = (ImageView) findViewById(R.id.atualizaImagemPrato);

                image.setText(imagems);
                numero.setText(numeros);
                nome.setText(nomes);
                ingrediente.setText(ingredientes);
                preco.setText(precos);
                Picasso.get()
                        .load(imagems)
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .into(atualizaImagemPrato);
            }

            atualizaImagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent pegaImagem = new Intent(
                            Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pegaImagem, IMAGEM_PEGA);
                }
            });
        }

        Button atualizarPrato = (Button) findViewById(R.id.atualizarPrato);
        Button excluirPrato = (Button) findViewById(R.id.excluirPrato);

        iniciaFireDatabase();
        iniciaFireStorage();

        atualizarPrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Modelo prato = new Modelo();
                prato.setNumeroPrato(numero.getText().toString());
                prato.setNomePrato(nome.getText().toString());
                prato.setPrecoPrato(preco.getText().toString());
                prato.setIngredientesPrato(ingrediente.getText().toString());
                prato.setImagemURL(image.getText().toString());

                if(nome.getText().toString().isEmpty()||
                        preco.getText().toString().isEmpty()||numero.getText().toString().isEmpty()||ingrediente.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"Preencha todos os campos",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(),"Prato atualizado",Toast.LENGTH_LONG).show();
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

                storageReference.child(nome.getText().toString()+".jpg").delete();
                databaseReference.child("Prato").child(prato.getNumeroPrato()).removeValue();
                Toast.makeText(getApplicationContext(),"Prato excluido",Toast.LENGTH_LONG).show();
                finish();

            }
        });
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== IMAGEM_PEGA  && resultCode == RESULT_OK) {
            Uri imagem = data.getData();
            Picasso.get().load(imagem).into(atualizaImagemPrato);
        }
    }

    private String pegarExtensão (Uri uri){
        ContentResolver contentR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(contentR.getType(uri));
    }

    private void iniciaFireStorage() {
        FirebaseApp.initializeApp(AtualizaExclui.this);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
    }

    private void iniciaFireDatabase() {
        FirebaseApp.initializeApp(AtualizaExclui.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

}
