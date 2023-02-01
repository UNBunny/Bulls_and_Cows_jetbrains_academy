package bullscows;

import java.util.*;

public class Game {
    private String[] answer;
    private int bulls;
    private int cows;
    private boolean status = true;

    public Game(String[] answer) {
        this.answer = answer;
    }

    public void Play(String number) {
        String[] strToArray = number.split("");
        int bulls = 0, cows = 0;
        int i = 0;
        for (i = 0; i < answer.length; i++) {
            if (answer[i].equals(strToArray[i])) { //secret
                bulls++;
                cows--;
            }
        }
        for (i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer.length; j++) {
                if (answer[j].equals(strToArray[i])) { //secret
                    cows++;
                }
            }
        }
        if (bulls == answer.length) {
            System.out.printf("Grade: %d bull(s)\n", bulls);
            System.out.println("Congratulations! You guessed the secret code.");
            status = false;
        } else if (bulls == 0 && cows == 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s)\n", bulls, cows);
        } else if (bulls != 0 && cows != 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s)\n", bulls, cows);
        } else if (bulls != 0) {
            System.out.printf("Grade: %d bull(s)\n", bulls);
        } else if (cows != 0) {
            System.out.printf("Grade: %d cows(s)\n", cows);
        }
    }
    public boolean getStatus() {
        return status;
    }
}
