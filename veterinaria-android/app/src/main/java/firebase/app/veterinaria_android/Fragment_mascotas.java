package firebase.app.veterinaria_android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import firebase.app.veterinaria_android.adapter.MascotaAdapter;
import firebase.app.veterinaria_android.modelo.Mascota;


public class Fragment_mascotas extends Fragment {

    FloatingActionButton btnAgregarMascota;
    RecyclerView recyclerMascotas;
    MascotaAdapter mascotaAdapter;
    FirebaseFirestore firestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mascotas, container, false);

        // configuracion firestore
        firestore = FirebaseFirestore.getInstance();
        Query query = firestore.collection("mascotas");
        FirestoreRecyclerOptions<Mascota> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Mascota>().setQuery(query,Mascota.class).build();

        // configuracion del adapter
        mascotaAdapter = new MascotaAdapter(firestoreRecyclerOptions);
        mascotaAdapter.notifyDataSetChanged();

        // configuracion del recycleMascotas
        recyclerMascotas = view.findViewById(R.id.recyclerMascotas);
        recyclerMascotas.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerMascotas.setAdapter(mascotaAdapter);

        // configuracion boton agregar
        btnAgregarMascota = view.findViewById(R.id.btnAgregarMascota);
        btnAgregarMascota.setBackgroundTintMode(null);
        btnAgregarMascota.setOnClickListener( click -> {
            Dialog_crearmascota modal = new Dialog_crearmascota();
            modal.show(getParentFragmentManager(),"Navegar");
        });
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        mascotaAdapter.startListening();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mascotaAdapter.stopListening();
    }
}