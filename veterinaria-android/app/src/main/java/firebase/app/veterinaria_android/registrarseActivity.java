package firebase.app.veterinaria_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegistrarseActivity extends AppCompatActivity {

    private EditText correo;
    private EditText contraseña1;
    private EditText contraseña2;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        correo = findViewById(R.id.txtCorreo);
        contraseña1 = findViewById(R.id.txtContraseña1);
        contraseña2 = findViewById(R.id.txtContraseña2);

        auth = FirebaseAuth.getInstance();
    }

    public void irIniciarSesion(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void crearCuenta(View view){
        if(contraseña1.getText().toString().equals(contraseña2.getText().toString())){
            String email = correo.getText().toString().equals("") ? " " : correo.getText().toString();
            String password = contraseña1.getText().toString().equals("") ? " " : contraseña1.getText().toString();
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener( task -> {
                if(task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Usuario creado correctamente, Bienvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Failed account creation", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, "Las contraseñas no coindicen", Toast.LENGTH_SHORT).show();
        }
    }
}