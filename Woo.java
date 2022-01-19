// AliensArrivingNaughtily -- Ameer Alnasser + Nakib Abedin + Alif Rahman
// APCS pd06
// FP - (Don't) Hang da Man
// 2022-01-18t
// time spent: 5 hrs

import java.io.*;
import java.util.*;

public class Woo {

  private static final int BRIGHT = 1;
  private static final int DARK = 2;
  private static final int ITALICS = 3;
  private static final int BLACK = 30;
  private static final int RED = 31;
  private static final int GREEN = 32;
  private static final int YELLOW = 33;
  private static final int BLUE = 34;
  private static final int MAGENTA = 35;
  private static final int CYAN = 36;
  private static final int WHITE = 37;
  private static final String CLEAR_SCREEN =  "\033[2J";
  private static final String HIDE_CURSOR =  "\033[?25l";
  private static final String SHOW_CURSOR =  "\033[?25h";

  //use this to go back to normal terminal colors
  private static final String RESET = color(40,37)+SHOW_CURSOR;

  //use this to convert from color to background (30 to 37 becomes 40 to 47)
  public static int background(int color)
  {
    return color + 10;
  }

  //terminal specific character to move the cursor to a location
  //top left is 1,1
  private static String go(int x, int y)
  {
    return ("\033[" + x + ";" + y + "H");
  }


  private static String color(int a, int b)
  {
    return ("\033[0;" + a+ ";" + b + "m");
  }
  private static String color(int a, int b, int c)
  {
    return ("\033[0;" + a+ ";" + b + ";" + c+ "m");
  }
  private static String color(int a, int b, int c, int d)
  {
    return ("\033[0;" + a+ ";" + b + ";" + c + ";" + d + "m");
  }


  //delay printing...
  private static void wait(int millis)
  {
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
    }
  }

  public static void main(String[] args){
    Game game= new Game();
    background(100);
    game.initialScreen();

    System.out.println("Select your category: ");
    System.out.println("0. Places \n1. Celebrities \n2. Movies \n3. Tofr's Wise Words \n4. Period 6 Thinkeren \n5. Quotes \n6. DIY");

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

    System.out.println(game.addGuess());
    System.out.println(game.guessArr);

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

          System.out.println("guessArr: " + game.printArr(game.guessArr));
          System.out.println("current: " + game.printArr(game.current));
          System.out.println((game.guessArr.equals(game.current)));
        }
        System.out.println("CONGRATS, YOU COMPLETED THE GAME!!!");
        break;
      }
  }
}
}
