package com.konrados.testconstraintlayout.viewController;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.konrados.testconstraintlayout.R;
import com.konrados.testconstraintlayout.service.ButtonyMain;
import com.konrados.testconstraintlayout.service.ButtonyRegisterView;
import com.konrados.testconstraintlayout.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding bin;
    ButtonyMain buttonyMain;
    ButtonyRegisterView buttonyRegisterView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        bin = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bin.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String login = getIntent().getStringExtra("login");
        buttonyRegisterView = new ButtonyRegisterView(this,login);
        //Obsługa klasy przycisku logowania i rejestracji
        buttonyMain = new ButtonyMain(bin,this);
        buttonyMain.loginClick();
        buttonyMain.registerClick();







    }
}