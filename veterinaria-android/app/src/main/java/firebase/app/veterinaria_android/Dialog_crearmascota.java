package firebase.app.veterinaria_android;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.DialogFragment;
import androidx.activity.result.contract.ActivityResultContracts;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Dialog_crearmascota extends DialogFragment {
    MaterialButton btnRegistrar, btnSubirFoto;
    TextInputEditText txtNombre,txtEdad, txtRaza;
    AutoCompleteTextView txtSexo;
    ImageView imgFoto;
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
        imgFoto = view.findViewById(R.id.imgFoto);
        btnSubirFoto = view.findViewById(R.id.btnSubirFoto);
        btnRegistrar = view.findViewById(R.id.btnRegistrar);

        // establecer seleccion del campo sexo
        String[] items = getResources().getStringArray(R.array.arraySexo);
        if (items.length > 0) txtSexo.setText(items[0], false);

        abrirGaleria(view);
        establecerClickBtnRegistrar();

        return view;
    }

    private void abrirGaleria(View view){
        ActivityResultLauncher<Intent> imagenSeleccionada = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(), activityResult -> {
            if (activityResult.getResultCode() == Activity.RESULT_OK && activityResult.getData() != null) {
                Uri selectedImage = activityResult.getData().getData();
                ImageView imageView = view.findViewById(R.id.imgFoto);
                imageView.setImageURI(selectedImage);
            }
        });
        establecerClickBtnSubirFoto(imagenSeleccionada);
    }


    private void establecerClickBtnSubirFoto(ActivityResultLauncher<Intent> imagenSeleccionada){
        btnSubirFoto.setOnClickListener( click -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imagenSeleccionada.launch(intent);
        });
    }

    private void establecerClickBtnRegistrar(){
        btnRegistrar.setOnClickListener( click -> {
            String nombremascota = "" + txtNombre.getText().toString().trim();
            String edadmascota = "" + txtEdad.getText().toString().trim();
            String razamascota = "" + txtRaza.getText().toString().trim();
            String sexomascota = "" + txtSexo.getText().toString().trim();

            if(nombremascota.isEmpty() && edadmascota.isEmpty() && razamascota.isEmpty()){
                Toast.makeText(getContext(), "Llenar todos los datos", Toast.LENGTH_SHORT).show();
            }else{
                registrarEnFirebase(nombremascota, edadmascota, razamascota, sexomascota);
            }
        });
    }

    private void registrarEnFirebase(String nombremascota, String edadmascota, String razamascota, String sexomascota){
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
