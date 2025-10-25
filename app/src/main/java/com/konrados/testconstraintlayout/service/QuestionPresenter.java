package com.konrados.testconstraintlayout.service;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.konrados.testconstraintlayout.api.Answer;
import com.konrados.testconstraintlayout.api.Question;
import com.konrados.testconstraintlayout.databinding.ActivityQuestionScreenBinding;
import com.konrados.testconstraintlayout.viewController.QuestionScreen;
import com.konrados.testconstraintlayout.viewController.ResultScreen;

import java.util.List;


public class QuestionPresenter {


    private List<Question> questions;

    private int currentQuestionIndex = 0;

    private ActivityQuestionScreenBinding bin;

    Context contextEkranuLogging;
    String login;

    private int totalPoints = 0;
    String category;


    public QuestionPresenter(ActivityQuestionScreenBinding bin, Context contextEkranuLogging, String login, String category) {

        this.bin = bin;

        this.contextEkranuLogging = contextEkranuLogging;
        this.login = login;
        this.category = category;

    }


    public void showQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Question currentQuestion = questions.get(currentQuestionIndex);

            bin.textViewQuestion.setText(currentQuestion.getQuestion());

            bin.answersRadioGroup.clearCheck();

            bin.answersRadioGroup.removeAllViews();

            for (Answer answer : currentQuestion.getAnswers()) {

                RadioButton radioButton = new RadioButton(contextEkranuLogging);

                radioButton.setText(answer.getAnswer());

                radioButton.setTag(answer.getPoints());

                radioButton.setTextColor(Color.WHITE);

                radioButton.setId(View.generateViewId());

                bin.answersRadioGroup.addView(radioButton);

            }

        } else {

            Toast.makeText(contextEkranuLogging, "Koniec testu! Zdobyte punkty: " + totalPoints, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(contextEkranuLogging, ResultScreen.class);
            intent.putExtra("totalPoints", totalPoints);
            intent.putExtra("CATEGORY", category);
            intent.putExtra("login", login);
            contextEkranuLogging.startActivity(intent);
            if (contextEkranuLogging instanceof QuestionScreen) {
                ((QuestionScreen) contextEkranuLogging).finish();
            }

        }

    }


    public void setQuestions(List<Question> questions) {

        this.questions = questions;

        this.currentQuestionIndex = 0;

        this.totalPoints = 0;

    }


    public void clickButtonNext() {

        bin.nextButton.setOnClickListener(v -> {


            if (bin.answersRadioGroup.getCheckedRadioButtonId() == -1) {

                Toast.makeText(contextEkranuLogging, "Wybierz odpowiedź!", Toast.LENGTH_SHORT).show();

            } else {

                int selectedRadioButtonId = bin.answersRadioGroup.getCheckedRadioButtonId();

                RadioButton selectedRadioButton = bin.answersRadioGroup.findViewById(selectedRadioButtonId);

                int points = (int) selectedRadioButton.getTag();

                totalPoints += points;

                currentQuestionIndex++;

                showQuestion();


            }


        });

    }
}