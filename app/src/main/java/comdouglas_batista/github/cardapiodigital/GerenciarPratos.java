package comdouglas_batista.github.cardapiodigital;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class GerenciarPratos extends Activity{

    private RecyclerView listaPrat;
    private AdapterLista AdapterLista;
    private ArrayAdapter<Modelo> arrayPratos;
    private List<Modelo> listaPrato;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_pratos);

        listaPrat = findViewById(R.id.RecyclePratos);
        listaPrat.setHasFixedSize(true);
        listaPrat.setLayoutManager(new LinearLayoutManager(this));

        listaPrato = new ArrayList<>();

        iniciaFirebase();
        pegaPratosDatabase();

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

                AdapterLista = new AdapterLista(GerenciarPratos.this, listaPrato);
                listaPrat.setAdapter(AdapterLista);
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