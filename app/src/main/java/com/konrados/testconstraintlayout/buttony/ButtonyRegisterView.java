package com.konrados.testconstraintlayout.buttony;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.konrados.testconstraintlayout.api.ApiClient;
import com.konrados.testconstraintlayout.api.User;
import com.konrados.testconstraintlayout.databinding.ActivityRegisterViewBinding;
import com.konrados.testconstraintlayout.viewController.MainActivity;
import com.konrados.testconstraintlayout.viewController.MainActivityLogging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ButtonyRegisterView {
    ActivityRegisterViewBinding bin;

    Context contextEkranu;

    public ButtonyRegisterView(Context contextEkranu,String login) {
        this.contextEkranu = contextEkranu;
    }
    public ButtonyRegisterView(ActivityRegisterViewBinding bin,Context contextEkranu,String login) {
        this.bin = bin;
        this.contextEkranu = contextEkranu;
    }


    public void registerClick() {
        bin.registerButton.setOnClickListener(v -> {

            if (bin.regLoginUser.getText().toString().isEmpty() || bin.passwordUser.getText().toString().isEmpty()) {
                Toast.makeText(contextEkranu,"Uzupełnij wszystkie pola",Toast.LENGTH_SHORT).show();
            }
            else{

                String login = bin.regLoginUser.getText().toString().trim();
                String password = bin.passwordUser.getText().toString().trim();
                User user = new User();
                user.setUsername(login);
                user.setPassword(password);
                ApiClient.getAuthApi().register(user).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
                            response.body();
                            Intent gotoLogin = new Intent(contextEkranu, MainActivityLogging.class);
                            gotoLogin.putExtra("login",login);
                            contextEkranu.startActivity(gotoLogin);
                            Toast.makeText(contextEkranu,"Rejestracja przebiegła pomyślnie",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            response.code();
                            Toast.makeText(contextEkranu,"Błąd rejestracji",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable throwable) {
                        Log.e("NET", "register failed", throwable);
                        Toast.makeText(contextEkranu, "Błąd sieci: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }

    public void loginClick(){
        bin.loginButton.setOnClickListener(v -> {
            Intent gotoLogin = new Intent(contextEkranu, MainActivity.class);
            contextEkranu.startActivity(gotoLogin);
        });
    }
}
