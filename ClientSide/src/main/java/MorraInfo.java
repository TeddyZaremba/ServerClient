import java.io.Serializable;

public class MorraInfo implements Serializable {
    public int p1Points;
    public int p2Points;
    public int p1Plays;
    public int p2Plays;
    public int p1Guess;
    public int p2Guess;
    public int total;
    public boolean have2Players;
    public boolean playAgain;
    public boolean p1Again;
    public boolean p2Again;
    public int p1Index; 
    public int p2Index;
    public boolean p1Entered;
    public boolean p2Entered;
    public boolean update;
    public int round;

    MorraInfo() {
        p1Points = 0;
        p2Points = 0;
        have2Players = true;
        p1Entered = false;
        p2Entered = false;
        playAgain = false;
        round = 1;
    }

    public void addGuess(int p, int guess, int index) {
        if (index == 1) {
            p1Plays = p;
            p1Guess = guess;
            p1Entered = true;
        } else { // else it's player 2
            p2Plays = p;
            p2Guess = guess;
            p2Entered = true;
        }
    }

    public int checkGuess() {
        int playerWon;
        total = p1Plays + p2Plays;
        if (p1Guess == total && p2Guess != total) {
            p1Points++;
            playerWon = 1;
        } else if (p1Guess != total && p2Guess == total) {
            p2Points++;
            playerWon = 2;
        } else {
            playerWon = 0;
        }
        p1Entered = false;
        p2Entered = false;
        update = true;
        round++;

        return playerWon;
    }
}