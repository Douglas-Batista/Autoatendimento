package comdouglas_batista.github.cardapiodigital;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class Prato extends Activity {

    TextView image, nome, numero, ingrediente, preco;
    Button atualizaImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prato_selecionado);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle dados = intent.getExtras();

            if (dados != null) {
                String numeros = dados.getString("numero");
                String nomes = dados.getString("nome");
                String ingredientes = dados.getString("ingredientes");
                String precos = dados.getString("preco");
                String imagems = dados.getString("imagem");

                image = (TextView) findViewById(R.id.gambiarra3);
                nome = (TextView) findViewById(R.id.TxvNPselec);
                ingrediente = (TextView) findViewById(R.id.TxvIPselec);
                preco = (TextView) findViewById(R.id.TxvPrePselec);
                numero = (TextView) findViewById(R.id.gambiarra4);
                ImageView imvFPselec = (ImageView) findViewById(R.id.ImvFPselec);

                image.setText(imagems);
                numero.setText(numeros);
                nome.setText(nomes);
                ingrediente.setText(ingredientes);
                preco.setText(precos);
                Picasso.get()
                        .load(imagems)
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .into(imvFPselec);
            }
        }
    }
}
