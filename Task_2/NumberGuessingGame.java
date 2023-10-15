import java.util.Scanner;

//importing scanner class
public class NumberGuessingGame {

    public static void main(String[] args) {
        // Generate a random number between 1 and 100
        int randNum = (int) (Math.random() * 100) + 1;

        // Create a scanner to read user input
        Scanner sc = new Scanner(System.in);
        System.out.println("enter no.of stages you want to play:");
        int stages = sc.nextInt();
        int i = 1;
        while (i <= stages) {
            System.out.println("--->stage_" + i);
            // rules of the game
            System.out.println("Welcome to the number guessing game!");
            System.out.println("the rules of the game are:");
            System.out.println("1.You have 10 tries to guess the number.");
            System.out.println("2.the number is in between 1 to 100");
            System.out.println("-----------------------------------");
            // Start the game
            int Count = 1;
            int maxCount = 10;
            while (Count <= 10) {
                // guess a number
                System.out.println("Enter your guess: ");
                int Guess = sc.nextInt();

                // Check if the user guessed the number correctly
                if (Guess == randNum) {
                    System.out.println("Congratulations! You guessed the number correctly in " + Count + " tries!");
                    break;
                } else if (Guess > randNum) {
                    System.out.println("Your guess is high.");
                } else {
                    System.out.println("Your guess is low.");
                }

                // Increment the guess count
                Count++;
            }

            // If the user did not guess the number correctly, tell them the correct number
            if (Count > 10) {
                System.out.println("Sorry, you did not guess the number correctly in given chances.");
                System.out.println("The correct number is : " + randNum);
            }
            // displaying score
            int score = maxCount - Count;
            System.out.println("----------------------------------");
            System.out.println("your score is:" + score + "out of 10");
            System.out.println("----------------------------------");
            // increasing stages
            i++;
        }

        // Close the scanner
        sc.close();
    }
}