package com.konrados.testconstraintlayout.service;

import android.content.Context;
import android.content.Intent;

import com.konrados.testconstraintlayout.databinding.ActivityMainLoggingBinding;
import com.konrados.testconstraintlayout.viewController.MainActivityLogging;
import com.konrados.testconstraintlayout.viewController.QuestionScreen;
import com.konrados.testconstraintlayout.viewController.RegisterView;

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
            intent.putExtra("login", login);
            intent.putExtra("CATEGORY", "adhd");
            context.startActivity(intent);
            if (context instanceof MainActivityLogging) {
                ((MainActivityLogging) context).finish();
            }
        });
    }

    public void clickButtonAnxiety() {
        bin.anxietyButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, QuestionScreen.class);
            intent.putExtra("CATEGORY", "anxiety");
            intent.putExtra("login", login);
            context.startActivity(intent);
            if (context instanceof MainActivityLogging) {
                ((MainActivityLogging) context).finish();
            }
        });
    }

    public void clickButtonDepression() {
        bin.depressionButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, QuestionScreen.class);
            intent.putExtra("CATEGORY", "depression");
            intent.putExtra("login", login);
            context.startActivity(intent);
            if (context instanceof MainActivityLogging) {
                ((MainActivityLogging) context).finish();
            }
        });
    }
}
