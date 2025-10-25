package com.konrados.testconstraintlayout.service;


import android.content.Context;
import android.content.Intent;

import com.konrados.testconstraintlayout.databinding.ActivityResultScreenBinding;
import com.konrados.testconstraintlayout.viewController.MainActivityLogging;
import com.konrados.testconstraintlayout.viewController.ResultScreen;

import java.util.Locale;

public class ResultScreenService {

    ActivityResultScreenBinding bin;
    Context context;
    String login;
    String category;


    public ResultScreenService(ActivityResultScreenBinding bin, Context context, String login,String category) {
        this.bin = bin;
        this.context = context;
        this.login = login;
        this.category = category;

    }

    public void result(int points) {
        bin.tvScore.setText(String.valueOf(points));

        // null-safe: znormalizuj nazwę kategorii
        final String cat = category == null ? "" : category.trim().toLowerCase(Locale.ROOT);

        String resultHelp;

        switch (cat) {
            case "adhd":
                if (points <= 40) {
                    resultHelp = "Brak obaw, twój wynik sugeruje, że raczej nie masz objawów wskazujących na problemy z koncentracją czy impulsywnością. Wszystko wygląda stabilnie.";
                } else if (points <= 70) { // 41–70
                    resultHelp = "Jeszcze git, masz pewne cechy, które mogą czasem utrudniać skupienie lub planowanie, ale ogólnie ogarniasz temat. Warto dbać o sen, rutynę i odpoczynek.";
                } else if (points <= 100) { // 71–100
                    resultHelp = "Zwróć uwagę na siebie — możesz doświadczać trudności z koncentracją, organizacją lub impulsywnością. Dobry moment, żeby przyjrzeć się temu bliżej.";
                } else { // > 100
                    resultHelp = "Skontaktuj się z psychologiem — wynik sugeruje większe nasilenie objawów charakterystycznych dla ADHD. To orientacyjna skala; warto skonsultować.";
                }
                break;

            case "anxiety":
                if (points <= 4) {
                    resultHelp = "Twój wynik nie wskazuje na objawy lęku. Masz dobrą równowagę emocjonalną — oby tak dalej!";
                } else if (points <= 9) { // 5–9
                    resultHelp = "Możesz czasem odczuwać stres, napięcie lub zmartwienia, ale to zupełnie normalne. Spróbuj poświęcić więcej czasu na relaks i regenerację.";
                } else if (points <= 14) { // 10–14
                    resultHelp = "Twój wynik sugeruje umiarkowany poziom lęku. Warto obserwować swoje samopoczucie i zadbać o sen, ruch oraz rozmowę z kimś, kto Cię wspiera.";
                } else if (points <= 21) { // 15–21
                    resultHelp = "Twój wynik wskazuje na silny lęk. Jeśli czujesz, że napięcie utrudnia Ci codzienne funkcjonowanie, nie zostawaj z tym sam — rozmowa z psychologiem może naprawdę pomóc.";
                } else { // > 21 (gdyby coś się rozjechało)
                    resultHelp = "Wynik poza zakresem skali GAD-7 — sprawdź obliczenia lub spróbuj ponownie.";
                }
                break;


            case "depression":
                if (points <= 11) {
                    resultHelp = "Brak depresji lub minimalne objawy. Twój wynik nie wskazuje na istotne obniżenie nastroju.";
                } else if (points <= 19) {
                    resultHelp = "Łagodna depresja. Możesz doświadczać obniżonego nastroju, mniejszej motywacji i trudności z koncentracją.";
                } else if (points <= 25) {
                    resultHelp = "Umiarkowana depresja. Wynik sugeruje zauważalne objawy – warto porozmawiać z kimś zaufanym lub specjalistą.";
                } else { // 26–63
                    resultHelp = "Ciężka depresja. Wynik wskazuje na duże nasilenie objawów depresyjnych. To nie wyrok, ale sygnał, że potrzebujesz wsparcia – psychologa lub psychiatry.";
                }
                break;


            default:
                resultHelp = "Nieznana kategoria wyniku.";
        }

        bin.tvResultMessage.setText(resultHelp);
    }


    public void buttonActionEnded() {
        bin.btnDone.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivityLogging.class);
            intent.putExtra("login", login);
            context.startActivity(intent);
            if ((context instanceof ResultScreen)){
                ((ResultScreen) context).finish();
            }
        });
    }
}
