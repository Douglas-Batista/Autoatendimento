package comdouglas_batista.github.cardapiodigital;

import android.media.Image;
import android.widget.ImageView;

public class Modelo {

    private String NumeroPrato;
    private String NomePrato;
    private String IngrdientesPrato;
    private String PrecoPrato;
    private Image ImagemPrato;

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

    public String getIngrdientesPrato() {
        return IngrdientesPrato;
    }

    public void setIngrdientesPrato(String ingrdientesPrato) {
        IngrdientesPrato = ingrdientesPrato;
    }

    public String getPrecoPrato() {
        return PrecoPrato;
    }

    public void setPrecoPrato(String precoPrato) {
        PrecoPrato = precoPrato;
    }

    public Image getImagemPrato() {
        return ImagemPrato;
    }

    public void setImagemPrato(Image imagemPrato) {
        ImagemPrato = imagemPrato;
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "NomePrato='" + NomePrato + '\'' +
                '}';
    }
}
