package com.konrados.testconstraintlayout.service;


import com.konrados.testconstraintlayout.databinding.ActivityResultScreenBinding;

public class ResultScreenService {

    ActivityResultScreenBinding bin;

    public ResultScreenService(ActivityResultScreenBinding bin) {
        this.bin = bin;
    }

    public void result(int points) {
        bin.tvScore.setText(String.valueOf(points));
        String resultHelp = "Error";

        if (points <= 40) {
            resultHelp = "Brak obaw, twój wynik sugeruje, że raczej nie masz objawów wskazujących na problemy z koncentracją czy impulsywnością. Wszystko wygląda stabilnie ";
        } else if (points > 40 && points <= 70) {
            resultHelp = "Jeszcze git, masz pewne cechy, które mogą czasem utrudniać skupienie lub planowanie, ale ogólnie ogarniasz temat. Warto dbać o sen, rutynę i odpoczynek ";
        } else if (points > 70 && points <= 100) {
            resultHelp = "Zwróć uwagę na siebie, twój wynik pokazuje, że możesz doświadczać trudności z koncentracją, organizacją lub impulsywnością. To dobry moment, żeby przyjrzeć się temu bliżej i porozmawiać z kimś zaufanym";
        } else if (points > 100) {
            resultHelp = "Skontaktuj się z psychologiem, wynik sugeruje większe nasilenie objawów charakterystycznych dla ADHD. Nie panikuj – to tylko orientacyjna skala, ale warto pogadać z psychologiem lub psychiatrą, żeby to dobrze sprawdzić";
        }
        bin.tvResultMessage.setText(resultHelp);
    }


}
