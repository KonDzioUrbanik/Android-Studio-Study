package com.konrados.testconstraintlayout.viewController;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.konrados.testconstraintlayout.R;
import com.konrados.testconstraintlayout.buttony.ButtonyMain;
import com.konrados.testconstraintlayout.buttony.ButtonyRegisterView;
import com.konrados.testconstraintlayout.databinding.ActivityMainBinding;
import com.konrados.testconstraintlayout.databinding.ActivityRegisterViewBinding;

public class RegisterView extends AppCompatActivity {
    ActivityRegisterViewBinding bin;
    ButtonyRegisterView buttonyRegisterView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        bin = ActivityRegisterViewBinding.inflate(getLayoutInflater());
        setContentView(bin.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String login = getIntent().getStringExtra("login");
        buttonyRegisterView = new ButtonyRegisterView(bin,this,login);
        buttonyRegisterView.registerClick();
        buttonyRegisterView.loginClick();





    }
}