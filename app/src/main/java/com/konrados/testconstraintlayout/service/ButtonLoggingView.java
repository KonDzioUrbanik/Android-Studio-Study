package com.konrados.testconstraintlayout.service;

import android.content.Context;
import android.content.Intent;

import com.konrados.testconstraintlayout.databinding.ActivityMainLoggingBinding;
import com.konrados.testconstraintlayout.viewController.QuestionScreen;

public class ButtonLoggingView {
    ActivityMainLoggingBinding bin;
    Context context;
    String login;

    public ButtonLoggingView(ActivityMainLoggingBinding bin, Context context, String login) {
        this.bin = bin;
        this.context = context;
        this.login = login;
    }


    public void pokazLogin() {
        bin.welcomeText.setText("Witaj " + login + "!");

    }

    public void clickButtonAdhd() {
        bin.adhdButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, QuestionScreen.class);
            context.startActivity(intent);
        });


    }
}
