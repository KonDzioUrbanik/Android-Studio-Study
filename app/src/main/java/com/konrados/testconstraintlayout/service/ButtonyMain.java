package com.konrados.testconstraintlayout.service;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.konrados.testconstraintlayout.api.ApiClient;
import com.konrados.testconstraintlayout.api.User;
import com.konrados.testconstraintlayout.databinding.ActivityMainBinding;
import com.konrados.testconstraintlayout.viewController.MainActivity;
import com.konrados.testconstraintlayout.viewController.MainActivityLogging;
import com.konrados.testconstraintlayout.viewController.RegisterView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ButtonyMain {

    ActivityMainBinding bin;
    private Context contextEkranu;

    public ButtonyMain(ActivityMainBinding bin, Context context) {
        this.bin = bin;
        this.contextEkranu = context;
    }

    public void registerClick() {
        bin.registerButton.setOnClickListener(v -> {
            Intent zmianaOkna = new Intent(contextEkranu, RegisterView.class);
            contextEkranu.startActivity(zmianaOkna);
            if (contextEkranu instanceof MainActivity) {
                ((MainActivity) contextEkranu).finish();
            }

        });
    }

    public void loginClick() {
        bin.zalogujButton.setOnClickListener(v -> {

            String login = bin.loginUser.getText().toString().trim();
            String password = bin.passwordUser.getText().toString().trim();

            boolean ok = true;
            if (login.isEmpty()) {
                bin.loginUser.setError("Wymagane");
                ok = false;
            }
            if (password.isEmpty()) {
                bin.passwordUser.setError("Wymagane");
                ok = false;
            }
            if (!ok) return;

            User user = new User();
            user.setUsername(login);
            user.setPassword(password);

            bin.zalogujButton.setEnabled(false);

            ApiClient.getAuthApi().login(user).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    bin.zalogujButton.setEnabled(true);

                    if (!response.isSuccessful()) {
                        Toast.makeText(contextEkranu, "Błąd: " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String msg = response.body();
                    if (msg == null) {
                        Toast.makeText(contextEkranu, "Pusta odpowiedź", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (msg.equals("User logged successfully")) {
                        Intent zmianaOkna = new Intent(contextEkranu, MainActivityLogging.class);
                        zmianaOkna.putExtra("login", login);
                        
                        contextEkranu.startActivity(zmianaOkna);
                        if (contextEkranu instanceof MainActivity) {
                            ((MainActivity) contextEkranu).finish();
                        }
                        Toast.makeText(contextEkranu, "Zalogowano pomyślnie", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(contextEkranu, msg, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable throwable) {
                    bin.zalogujButton.setEnabled(true);
                    Toast.makeText(contextEkranu, "Błąd sieci: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
