package firebase.app.veterinaria_android;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Dialog_crearmascota extends DialogFragment {

    Button btnRegistrar;
    TextInputEditText txtNombre,txtEdad, txtRaza;
    AutoCompleteTextView txtSexo;
    private FirebaseFirestore firestore;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_crearmascota, container, false);
        firestore = FirebaseFirestore.getInstance();

        txtNombre = view.findViewById(R.id.txtNombre);
        txtEdad = view.findViewById(R.id.txtEdad);
        txtRaza = view.findViewById(R.id.txtRaza);
        txtSexo = view.findViewById(R.id.txtSexo);
        btnRegistrar = view.findViewById(R.id.btnRegistrar);

        String[] items = getResources().getStringArray(R.array.arraySexo);
        if (items.length > 0) txtSexo.setText(items[0], false);

        btnRegistrar.setOnClickListener( click -> {
            String nombremascota = "" + txtNombre.getText().toString().trim();
            String edadmascota = "" + txtEdad.getText().toString().trim();
            String razamascota = "" + txtRaza.getText().toString().trim();
            String sexomascota = "" + txtSexo.getText().toString().trim();

            if(nombremascota.isEmpty() && edadmascota.isEmpty() && razamascota.isEmpty()){
                Toast.makeText(getContext(), "Llenar todos los datos", Toast.LENGTH_SHORT).show();
            }else{
                postpet(nombremascota, edadmascota, razamascota, sexomascota);
            }

        });
        return view;
    }

    private void postpet(String nombremascota, String edadmascota, String razamascota, String sexomascota){
        Map<String, Object> map = new HashMap<>();
        map.put("nombre",nombremascota);
        map.put("edad",edadmascota);
        map.put("raza",razamascota);
        map.put("sexo",sexomascota);

        firestore.collection("mascotas").add(map).addOnSuccessListener( documentReference -> {
            Toast.makeText(getContext(), nombremascota + " registrad" + nombremascota == "MACHO" ? "o":"a"  + " exitosamente", Toast.LENGTH_SHORT).show();
            getDialog().dismiss();
        }).addOnFailureListener(exception -> {
            Toast.makeText(getContext(), "Ocurrio un error al registrar", Toast.LENGTH_SHORT).show();
        });
    }
}
