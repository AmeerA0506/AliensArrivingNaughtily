// AliensArrivingNaughtily -- Ameer Alnasser + Nakib Abedin + Alif Rahman
// APCS pd06
// FP - (Don't) Hang da Man
// 2022-01-20r
// time spent: 15 hrs

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Woo {

  // terminal hax
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

  // All copied and pasted from ap251/library/TerminallyIll.java
  // Thank you Mr. Mykolyk!
  

  private static String go(int x, int y)
  // copied and pasted from TerminallIll.java
    {
      return ("\033[" + x + ";" + y + "H");
    }

  public static void main(String[] args){
    int guessCtr=0;
    Game game= new Game();
    Hangman hangman = new Hangman();
    Timer timer = new Timer();
    List<Character> wrongGuesses = new ArrayList<>();

    System.out.println(CLEAR_SCREEN);
    System.out.println(go(1,1));
    // Clears the terminal before starting the game for a better user interface
    game.initialScreen(); // Home Screen for the game
    System.out.println(HIDE_CURSOR);

    Scanner enter = new Scanner(System.in);

    if(enter.hasNextLine()){
      System.out.println(CLEAR_SCREEN);
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

    System.out.println(CLEAR_SCREEN);
    System.out.println(go(1,1));

    game.addGuess(); // randomly pick the item that the user will be guessing
    System.out.println(hangman.returnDrawing(guessCtr));
    System.out.println(game.returnArr(game.current));
    System.out.println("Incorrect Guesses: " + game.returnWrongGuesses(wrongGuesses));
    timer.startTimer();

    char q; // this var will be used for the user input
    ArrayList<Integer> p = new ArrayList<Integer>(); // this var will be used to find the indexOf q in the item being guessed
    String f="";
    List<Character> pain = new ArrayList<Character>();
    boolean isABigBoi = false;

    while(! (game.guessArr.equals(game.current)) ){
      if(guessCtr==6){
        System.out.println(CLEAR_SCREEN);
        System.out.println(go(1,1));
        System.out.println(hangman.returnDrawing(guessCtr));
        timer.stopTimer();
        timer.printSimplifiedTime(timer.getTimeElapsed());
        System.out.println("You have run out of guesses :(");
        System.out.println("Better luck next time!");
        return;
      }
      System.out.println("Would you like to guess the phrase? (Y/N): ");
      Scanner bigBoiGuess = new Scanner(System.in);
      while (bigBoiGuess.hasNextLine()){
        f=bigBoiGuess.nextLine();

        if((f.toLowerCase().equals("y"))||(f.toLowerCase().equals("yes"))){
          System.out.println("Feeling lucky I see? Enter your guess here:");
          f=bigBoiGuess.nextLine();
          for(char c:f.toCharArray()){
             pain.add(c);
             pain.add(' ');
         }
          if(game.returnArr(pain).toUpperCase().equals(game.returnArr(game.guessArr))){
            System.out.println("Correct! I'm impressed");
            isABigBoi = true;
            timer.stopTimer();
            System.out.print("Time: ");
            timer.printSimplifiedTime(timer.getTimeElapsed());
          }else{
            guessCtr+=1;
            System.out.println(hangman.returnDrawing(guessCtr));
            System.out.println(CLEAR_SCREEN);
            System.out.println(go(1,1));
            System.out.println(hangman.returnDrawing(guessCtr));
            System.out.println(game.returnArr(game.current));
            System.out.println("Incorrect...");
            System.out.println("Incorrect Guesses: " + game.returnWrongGuesses(wrongGuesses));
            System.out.println("Would you like to guess the phrase? (Y/N): ");
            continue;
          }
        }else if((f.toLowerCase().equals("n"))||(f.toLowerCase().equals("no"))){
          System.out.println(CLEAR_SCREEN);
          System.out.println(go(1,1));
          System.out.println(hangman.returnDrawing(guessCtr));
          System.out.println(game.returnArr(game.current));
          System.out.println("Incorrect Guesses: " + game.returnWrongGuesses(wrongGuesses));
        }else{
          System.out.println("We'll take that as a no");
        }
        break;

      }

      if(isABigBoi){
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
        if(p.size()==0){
          guessCtr += 1;
          wrongGuesses.add(q);
          System.out.println(CLEAR_SCREEN);
          System.out.println(go(1,1));
          System.out.println(hangman.returnDrawing(guessCtr));
          System.out.println("Incorrect...");
          System.out.println(game.returnArr(game.current));
          System.out.println("Incorrect Guesses: " + game.returnWrongGuesses(wrongGuesses));
          break;
        }
        System.out.println(CLEAR_SCREEN);
        System.out.println(go(1,1));
        System.out.println(hangman.returnDrawing(guessCtr));
        System.out.println(game.returnArr(game.current));
        System.out.println("Incorrect Guesses: " + game.returnWrongGuesses(wrongGuesses));

        break;

      }
  }
  System.out.println(SHOW_CURSOR); // don't want to get sued for making the user's cursor disappear

}// end of main method
}// end of class
