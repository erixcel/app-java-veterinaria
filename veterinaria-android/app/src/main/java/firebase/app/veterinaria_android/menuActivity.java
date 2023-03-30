package firebase.app.veterinaria_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity {

    private BottomNavigationView btnNavigationMenu;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        drawerLayout=findViewById(R.id.drawerlayout1);
        navigationView=findViewById(R.id.navigationview1);
        toolbar=findViewById(R.id.toolbar1);
        btnNavigationMenu = findViewById(R.id.btnNavigationMenu);

        // mostrar colores de botones de abajo y establecer seleccion
        btnNavigationMenu.setItemIconTintList(null);
        btnNavigationMenu.setSelectedItemId(R.id.interfaz_adoptar);

        // configuracion del toolbar
        ActionBarDrawerToggle actionBarDrawerToggle1= new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle1);
        actionBarDrawerToggle1.syncState();

        // estableciendo ventana por defecto
        getSupportFragmentManager().beginTransaction().add(R.id.frame1, new Fragment_adoptar()).commit();

        // configuracion menu lateral
        navigationView.setNavigationItemSelectedListener( item -> {
            switch (item.getItemId()){
                case R.id.menu_mascotas:getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new Fragment_mascotas()).commit();
                    break;
                case R.id.menu_web:getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new Fragment_web()).commit();
                    break;
                case R.id.menu_libros:getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new Fragment_libros()).commit();
                    break;
            }
            drawerLayout.closeDrawers();
            return false;
        });

        // configuracion botones de abajo
        btnNavigationMenu.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.interfaz_citas:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame1, new Fragment_citas()).commit();
                    return true;
                case R.id.interfaz_dar_adopcion:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame1, new Fragment_adopcion()).commit();
                    return true;
                case R.id.interfaz_adoptar:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame1, new Fragment_adoptar()).commit();
                    return true;
                case R.id.interfaz_pendientes:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame1, new Fragment_pendientes()).commit();
                    return true;
            }
            return false;
        });
    }
}