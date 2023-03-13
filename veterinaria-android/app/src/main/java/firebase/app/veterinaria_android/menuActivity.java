package firebase.app.veterinaria_android;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}