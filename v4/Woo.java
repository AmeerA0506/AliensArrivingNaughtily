// AliensArrivingNaughtily -- Ameer Alnasser + Nakib Abedin + Alif Rahman
// APCS pd06
// FP - (Don't) Hang da Man
// 2022-01-18t
// time spent: 5 hrs

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Woo {

  private static String go(int x, int y)
    {
      return ("\033[" + x + ";" + y + "H");
    }


  public static void main(String[] args){
    Game game= new Game();
    System.out.println("\033[2J");
    System.out.println(go(1,1));
    // Clears the terminal before starting the game for a better user interface
    game.initialScreen(); // Home Screen for the game
    System.out.println("\033[?25l");

    Scanner enter = new Scanner(System.in);

    if(enter.hasNextLine()){
      System.out.println("\033[2J");
      System.out.println(go(1,1));
      System.out.println("Select your category: ");
      System.out.println("0. Places \n1. Celebrities \n2. Movies \n3. Tofr's Wise Words \n4. Period 6 Thinkeren \n5. Quotes \n6. DIY");
    }

    Scanner sc = new Scanner(System.in);

    int category = sc.nextInt();
    switch(category){ //Inspired by Stuy alumnus and current Cornell TA, aka Ameer's brother
      case 0:
          game.populate("inputs/Places.in");
          break;
      case 1:
          game.populate("inputs/Celebrities.in");
          break;
      case 2:
          game.populate("inputs/Movies.in");
          break;
      case 3:
          game.populate("inputs/TofrsWords.in");
          break;
      case 4:
          game.populate("inputs/Thinkeren.in");
          break;
      case 5:
          game.populate("inputs/Quotes.in");
          break;
      case 6:
          System.out.println("Alif likes chicken nuggets");
          break;
        }
    System.out.println("\033[2J");
    System.out.println(go(1,1));

    game.addGuess(); // randomly pick the item that the user will be guessing
    System.out.println(game.printArr(game.current));  // diag

    char q; // this var will be used for the user input
    ArrayList<Integer> p = new ArrayList<Integer>(); // this var will be used to find the indexOf q in the item being guessed

    while(! (game.guessArr.equals(game.current)) ){
      System.out.println("Enter your guess: ");
      Scanner guess = new Scanner(System.in);
      while(guess.hasNextLine()){
        q = guess.next().charAt(0);
        p = game.find(q);
        for(int e : p){
          game.current.set(e, game.guessArr.get(e));
        }
        System.out.println("\033[2J");
        System.out.println(go(1,1));
        System.out.println(game.printArr(game.current));

        break;

      }
    System.out.println("\033[?25h"); // don't want to get sued for making the user's cursor disappear
  }
  System.out.println("Congrats, you completed the game!");

}
}
