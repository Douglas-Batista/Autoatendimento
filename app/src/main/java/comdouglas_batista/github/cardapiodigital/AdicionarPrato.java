package comdouglas_batista.github.cardapiodigital;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdicionarPrato extends Activity {

    private static int IMAGEM_PEGA = 1;
    private ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_prato);

        imagem = (ImageView) findViewById(R.id.imagemPrato);
        Button pegaimagem = (Button) findViewById(R.id.SelImagem);

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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== IMAGEM_PEGA  && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            imagem.setImageURI(fullPhotoUri);
        }
    }
}

