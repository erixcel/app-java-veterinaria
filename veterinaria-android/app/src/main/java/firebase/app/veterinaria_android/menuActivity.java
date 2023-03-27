package firebase.app.veterinaria_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class menuActivity extends AppCompatActivity {

    private BottomNavigationView btnNavigationMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnNavigationMenu = findViewById(R.id.btnNavigationMenu);
        btnNavigationMenu.setItemIconTintList(null);
        btnNavigationMenu.setSelectedItemId(R.id.interfaz_adoptar);

        getSupportFragmentManager().beginTransaction().add(R.id.frameMenu, new fragment_adoptar()).commit();

        btnNavigationMenu.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.interfaz_citas:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameMenu, new fragment_citas()).commit();
                    return true;
                case R.id.interfaz_dar_adopcion:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameMenu, new fragment_adopcion()).commit();
                    return true;
                case R.id.interfaz_adoptar:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameMenu, new fragment_adoptar()).commit();
                    return true;
                case R.id.interfaz_pendientes:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameMenu, new fragment_pendientes()).commit();
                    return true;
            }
            return false;
        });
    }
}