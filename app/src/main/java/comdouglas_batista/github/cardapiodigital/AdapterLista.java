package comdouglas_batista.github.cardapiodigital;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterLista extends RecyclerView.Adapter<AdapterLista.ImagemHolder> {

    private OnItemClickListener liste;
    private Context mContext;
    private List<Modelo> mUpload;
    Modelo pratoSelecionado;

    public AdapterLista(Context context, List<Modelo> uploads){
        mContext = context;
        mUpload = uploads;
    }

    @NonNull
    @Override
    public ImagemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.modelo_linha_lista, parent,false);
        return new ImagemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagemHolder holder, int position) {
        Modelo modelo = mUpload.get(position);
        holder.textNome.setText(modelo.getNomePrato());
        holder.textPreco.setText(modelo.getPrecoPrato());
        Picasso.get()
                .load(modelo.getImagemURL())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .into(holder.imagePrato);
    }

    @Override
    public int getItemCount() {
        return mUpload.size();
    }

    public class ImagemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textNome;
        TextView textPreco;
        ImageView imagePrato;

        ImagemHolder(View itemView) {
            super(itemView);

            textNome = itemView.findViewById(R.id.textnump);
            textPreco = itemView.findViewById(R.id.recebePreco);
            imagePrato = itemView.findViewById(R.id.imagep1);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View itemView) {
                    Intent intent = new Intent(itemView.getContext(), AtualizaExclui.class);

                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {

                        Modelo pratoSelecionado = mUpload.get(position);
                        Bundle dados = new Bundle();
                        dados.putString("nome", pratoSelecionado.getNomePrato());
                        dados.putString("numero", pratoSelecionado.getNumeroPrato());
                        dados.putString("ingredientes", pratoSelecionado.getIngredientesPrato());
                        dados.putString("preco", pratoSelecionado.getPrecoPrato());
                        dados.putString("imagem", pratoSelecionado.getImagemURL());
                        intent.putExtras(dados);
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (liste != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    liste.onItemClick(position);
                }
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
