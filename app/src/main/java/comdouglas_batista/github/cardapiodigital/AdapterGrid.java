package comdouglas_batista.github.cardapiodigital;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.ImagemHolder>{
    private AdapterLista.OnItemClickListener liste;
    private Context mContext;
    private List<Modelo> mUpload;

    public AdapterGrid(Context context, List<Modelo> uploads){
        mContext = context;
        mUpload = uploads;
    }

    @NonNull
    @Override
    public AdapterGrid.ImagemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_grid_menu, parent,false);
        return new AdapterGrid.ImagemHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterGrid.ImagemHolder holder, int position) {
        Modelo modelo = mUpload.get(position);
        holder.txvNomeP.setText(modelo.getNomePrato());
        holder.txvpreco.setText(modelo.getPrecoPrato());
        Picasso.get()
                .load(modelo.getImagemURL())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .into(holder.ImvPrato);
    }

    @Override
    public int getItemCount() {
        return mUpload.size();
    }

    public class ImagemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txvNomeP;
        TextView txvpreco;
        ImageView ImvPrato;

        ImagemHolder(View itemView) {
            super(itemView);

            txvNomeP = itemView.findViewById(R.id.txvNomeP);
            txvpreco = itemView.findViewById(R.id.TxvRecebePreco);
            ImvPrato = itemView.findViewById(R.id.ImvPrato);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View itemView) {
                    Intent intent = new Intent(itemView.getContext(), Prato.class);

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
}

