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
import com.konrados.testconstraintlayout.api.ApiClient;
import com.konrados.testconstraintlayout.api.Question;
// <-- POPRAWKA 1: Importujesz poprawny binding
import com.konrados.testconstraintlayout.databinding.ActivityQuestionScreenBinding;
import com.konrados.testconstraintlayout.service.QuestionPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionScreen extends AppCompatActivity {

    private ActivityQuestionScreenBinding bin;

    private QuestionPresenter questionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        bin = ActivityQuestionScreenBinding.inflate(getLayoutInflater());
        setContentView(bin.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        String login = getIntent().getStringExtra("login");
        String category = getIntent().getStringExtra("CATEGORY");




        questionPresenter = new QuestionPresenter(bin, this,login,category);

        questionPresenter.clickButtonNext();

        //Wczytywanie pytań i odpowiedzi



        ApiClient.getAuthApi().getQuestionsByCategory(category).enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(QuestionScreen.this,
                            "Nie udało się pobrać pytań: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                questionPresenter.setQuestions(response.body());
                questionPresenter.showQuestion();
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(QuestionScreen.this,
                        "Błąd sieci: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}