// AliensArrivingNaughtily -- Ameer Alnasser + Nakib Abedin + Alif Rahman
// APCS pd06
// FP - (Don't) Hang da Man
// 2022-01-18t
// time spent: 5 hrs

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Woo {

  private static String go(int x, int y)
    {
      return ("\033[" + x + ";" + y + "H");
    }


  public static void main(String[] args){
    int guessCtr=0;
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
    System.out.println(game.returnArr(game.current));  // diag

    char q; // this var will be used for the user input
    ArrayList<Integer> p = new ArrayList<Integer>(); // this var will be used to find the indexOf q in the item being guessed
    String f="";
    List<Character> pain = new ArrayList<Character>();
    while(! (game.guessArr.equals(game.current)) ){
      System.out.println("Would you like to guess the phrase? (Y/N): ");
      Scanner bigboiguess = new Scanner(System.in);
      while (bigboiguess.hasNextLine()){
        f=bigboiguess.nextLine();
        if((f.toLowerCase().equals("y"))||(f.toLowerCase().equals("yes"))){
          System.out.println("Feeling lucky I see? Enter your guess here:");
          f=bigboiguess.nextLine();
          for(char c:f.toCharArray()){
             pain.add(c);
             pain.add(' ');
         }
          if(game.returnArr(pain).toUpperCase().equals(game.returnArr(game.guessArr))){
            System.out.println("Correct! I'm impressed");
          }
          else{
            guessCtr+=1;
            System.out.println(game.returnArr(game.guessArr)); //diag
          }
          break;
        }
        break;
      }
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
        System.out.println(game.returnArr(game.current));

        break;

      }
    System.out.println("\033[?25h"); // don't want to get sued for making the user's cursor disappear
  }
  System.out.println("Congrats, you completed the game!");

}
}
