package comdouglas_batista.github.cardapiodigital;

import android.media.Image;
import android.widget.ImageView;

public class Modelo {

    private String NumeroPrato;
    private String NomePrato;
    private String IngredientesPrato;
    private String PrecoPrato;
    private String ImagemPrato;
    private String ImagemURL;

    public Modelo() {
    }

    public String getNumeroPrato() {
        return NumeroPrato;
    }

    public void setNumeroPrato(String numeroPrato) {
        NumeroPrato = numeroPrato;
    }

    public String getNomePrato() {
        return NomePrato;
    }

    public void setNomePrato(String nomePrato) {
        NomePrato = nomePrato;
    }

    public String getIngredientesPrato() {
        return IngredientesPrato;
    }

    public void setIngredientesPrato(String ingredientesPrato) {
        IngredientesPrato = ingredientesPrato;
    }

    public String getPrecoPrato() {
        return PrecoPrato;
    }

    public void setPrecoPrato(String precoPrato) {
        PrecoPrato = precoPrato;
    }

    public String getImagemPrato() {
        return ImagemPrato;
    }

    public void setImagemPrato(String imagemPrato) {
        ImagemPrato = imagemPrato;
    }

    public String getImagemURL() {
        return ImagemURL;
    }

    public void setImagemURL(String imagemURL) {
        ImagemURL = imagemURL;
    }

    @Override
    public String toString() {
        return  NomePrato + PrecoPrato + ImagemURL;
    }
}
