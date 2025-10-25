package com.konrados.testconstraintlayout.viewController;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.konrados.testconstraintlayout.R;
import com.konrados.testconstraintlayout.service.ResultScreenService;
import com.konrados.testconstraintlayout.databinding.ActivityResultScreenBinding;

public class ResultScreen extends AppCompatActivity {
    ActivityResultScreenBinding bin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        
        bin = ActivityResultScreenBinding.inflate(getLayoutInflater());
        setContentView(bin.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String login = getIntent().getStringExtra("login");
        int totalPoints = getIntent().getIntExtra("totalPoints", 0);
        String category = getIntent().getStringExtra("CATEGORY");
        Toast.makeText(this, "Total points: " + totalPoints, Toast.LENGTH_SHORT).show();
        ResultScreenService resultScreenService = new ResultScreenService(bin, this,login,category);
        resultScreenService.result(totalPoints);
        resultScreenService.buttonActionEnded();


    }
}