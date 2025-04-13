import java.util.Random;
import java.util.Scanner;

public class Guess {

    public static void main(String[] args) {

        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        int randomNumber = rand.nextInt(0, 101);
        // System.out.println("Random number is "+ randomNumber);

        int tryCount = 0;

        while (true) {
            System.out.println("Enter your guess (1-100): ");
            int playAgain = 0;
            int playerGuess = scanner.nextInt(); // gets int as an input from the user
            tryCount++;

            if (playerGuess == randomNumber) {
                System.out.println("Correct! The number is " + randomNumber);
                System.out.println("It took you " + tryCount + " tries");
                System.out.println("Input 1 if you want to play again");
                playAgain = scanner.nextInt();
                if (playAgain == 1) {
                    tryCount = 0;
                    randomNumber = rand.nextInt(0, 101);
                    continue;
                }
                else{
                    break;
                }

            } else if (randomNumber > playerGuess) {
                System.out.println("Nope! The number is higher");
            } else {
                System.out.println("Nope! The number is lower");
            }
        }
        scanner.close();
    }
}