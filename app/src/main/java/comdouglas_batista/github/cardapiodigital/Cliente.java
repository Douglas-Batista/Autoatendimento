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

public class Cliente extends Activity {

    private RecyclerView gridPrat;
    private AdapterGrid AdapterGrid;
    private ArrayAdapter<Modelo> arrayPratos;
    private List<Modelo> listaPrato;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);

        gridPrat = findViewById(R.id.recyclerCliente);
        gridPrat.setHasFixedSize(true);
        gridPrat.setLayoutManager(new LinearLayoutManager(this));

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

                AdapterGrid = new AdapterGrid(Cliente.this, listaPrato);
                gridPrat.setAdapter(AdapterGrid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void iniciaFirebase() {

        FirebaseApp.initializeApp(Cliente.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

}