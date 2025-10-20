package com.konrados.testconstraintlayout.buttony;

import android.content.Context;

import com.konrados.testconstraintlayout.databinding.ActivityMainLoggingBinding;

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
}
