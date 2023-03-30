package firebase.app.veterinaria_android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Fragment_mascotas extends Fragment {

    FloatingActionButton btnAgregarMascota;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mascotas, container, false);
        btnAgregarMascota = view.findViewById(R.id.btnAgregarMascota);

        // quitar fondo por defecto del boton
        btnAgregarMascota.setBackgroundTintMode(null);

        // establecer metodo click
        btnAgregarMascota.setOnClickListener( click -> {
            Dialog_crearmascota modal = new Dialog_crearmascota();
            modal.show(getParentFragmentManager(),"Navegar");
        });
        return view;
    }
}