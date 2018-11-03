package comdouglas_batista.github.cardapiodigital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GerenciarPratos extends Activity {

    ListView listaPratos;
    private List<Modelo> listaPrato = new ArrayList<>();
    private ArrayAdapter<Modelo> arrayPratos;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Modelo pratoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_pratos);
        listaPratos = (ListView) findViewById(R.id.listaPratos);

        iniciaFirebase();
        pegaPratosDatabase();

        listaPratos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pratoSelecionado = (Modelo) parent.getItemAtPosition(position);

                Bundle dados = new Bundle();
                dados.putString("nome", pratoSelecionado.getNomePrato());
                dados.putString("numero", pratoSelecionado.getNumeroPrato());
                dados.putString("ingredientes", pratoSelecionado.getIngredientesPrato());
                dados.putString("preco", pratoSelecionado.getPrecoPrato());
                Intent intent = new Intent(GerenciarPratos.this, AtualizaExclui.class);
                intent.putExtras(dados);
                startActivity(intent);
            }
        });
    }

    private void pegaPratosDatabase() {
        databaseReference.child("Prato").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listaPrato.clear();
                for (DataSnapshot objeto : dataSnapshot.getChildren()) {
                    Modelo prato = objeto.getValue(Modelo.class);
                    listaPrato.add(prato);
                }

                arrayPratos = new ArrayAdapter<Modelo>(GerenciarPratos.this,android.R.layout.simple_list_item_1,listaPrato);
                listaPratos.setAdapter(arrayPratos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void iniciaFirebase() {

        FirebaseApp.initializeApp(GerenciarPratos.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}
