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

  // // terminal hax
  // private static final int BRIGHT = 1;
  // private static final int DARK = 2;
  // private static final int ITALICS = 3;
  // private static final int BLACK = 30;
  private static final int RED = 31;
  private static final int GREEN = 32;
  private static final int YELLOW = 33;
  private static final int BLUE = 34;
  // private static final int MAGENTA = 35;
  // private static final int CYAN = 36;
  private static final int WHITE = 37;
  private static final String CLEAR_SCREEN =  "\033[2J";
  private static final String HIDE_CURSOR =  "\033[?25l";
  private static final String SHOW_CURSOR =  "\033[?25h";
  // All copied and pasted from ap251/library/TerminallyIll.java
  // Thank you Mr. K!

  private static void clear()
  // Inspired from TerminallyIll.java
  // Clears the screen and makes the cursor move to the top left of the terminal
    {
      System.out.println(CLEAR_SCREEN);
      System.out.println("\033[" + 1 + ";" + 1 + "H");
    }

  public static void main(String[] args){
    int guessCtr=0;
    Game game= new Game();
    Hangman hangman = new Hangman();
    Timer timer = new Timer();
    List<Character> wrongGuesses = new ArrayList<>();

    clear();
    game.initialScreen(); // Home Screen for the game
    System.out.println(HIDE_CURSOR); // Hides cursor -- it just looks nicer.

    Scanner enter = new Scanner(System.in); // Init Scanner to take in input for categories

    if(enter.hasNextLine()){
      clear();
      System.out.println("Welcome to Dont Hang da Man!");
      System.out.println("Select your difficulty:");
      System.out.println("0-1: Easy");
      System.out.println("2-3: Medium");
      System.out.println("4-5: Difficult");
      System.out.println("6: Custom");
  //    System.out.println("0. Places \n1. Celebrities \n2. Movies \n3. Tofr's Wise Words \n4. Period 6 Thinkeren \n5. Quotes \n6. DIY");
    }

    Scanner sc = new Scanner(System.in);
    String hint="";
    int category = sc.nextInt();
    switch(category){ //Inspired by Stuy alumnus and current Cornell TA, aka Ameer's brother
      case 0:
          game.populate("inputs/StuyAndAround.in");
          hint="Near or in Stuyvesant";
          break;
      case 1:
          game.populate("inputs/Thinkeren.in");
          hint="Thinkeren from period 6";
          break;
      case 2:
          game.populate("inputs/Movies.in");
          hint="Movies";
          break;
      case 3:
          game.populate("inputs/Celebrities.in");
          hint="Celebrities";
          break;
      case 4:
          game.populate("inputs/Places.in");
          hint="Places";
          break;
      case 5:
          game.populate("inputs/TofrsWords.in");
          hint="The wise words of Tofr";
          break;
      case 6:
          clear();
          System.out.println("Please enter the phrases you would like to add to the word bank!");
          System.out.println("Type in \"exit\" when you are done adding phrases!");
          Scanner diy = new Scanner(System.in);
          String item;
          while (diy.hasNextLine()) {
            item = diy.nextLine();
            if (item.toLowerCase().equals("exit")) {
              break;
            }
            game.wordBank.add(item);
          }
          break;
        }

    clear();
    game.addGuess(); // randomly pick the item that the user will be guessing
    System.out.println(hangman.returnDrawing(guessCtr));
    System.out.println(game.returnColoredCurrent());
    System.out.println("Incorrect Guesses: " + game.colorWrongGuesses(wrongGuesses));
    timer.startTimer();

    char q; // this var will be used for the user input
    ArrayList<Integer> p = new ArrayList<Integer>(); // this var will be used to find every index of q in the word being guessed
    String f="";
    List<Character> phraseGuess = new ArrayList<Character>();
    boolean isABigBoi = false;

    while(! (game.guessArr.equals(game.current)) ){
      if(guessCtr==6){
        clear();
        System.out.println(hangman.returnDrawing(guessCtr));
        timer.stopTimer();
        System.out.print("Time: " + game.color(BLUE));
        timer.printSimplifiedTime(timer.getTimeElapsed());
        System.out.println("\n" + game.color(RED) + "You have run out of guesses :(" + game.color(WHITE));
        System.out.println(game.color(GREEN) + "Better luck next time!" + game.color(WHITE));
        System.out.println("The phrase was " + game.color(YELLOW)+ game.guess);
        return;
      }

      System.out.print("Would you like to guess the entire phrase? ("+game.color(GREEN)+"Y/"+game.color(RED)+"N"+game.color(WHITE)+"): ");
      Scanner bigBoiGuess = new Scanner(System.in);

      while (bigBoiGuess.hasNextLine()){
        f=bigBoiGuess.nextLine();
        if((f.toLowerCase().equals("y"))||(f.toLowerCase().equals("yes"))){
          System.out.print("Feeling "+game.color(GREEN)+"lucky"+game.color(WHITE)+" I see? Enter your guess here:");
          f=bigBoiGuess.nextLine();
          for(char c: f.toCharArray()){
             phraseGuess.add(c);
             phraseGuess.add(' ');
         }
          if(game.returnArr(phraseGuess).toUpperCase().equals(game.returnArr(game.guessArr))){
            timer.stopTimer();
            clear();
            System.out.println(game.returnDrawing(guessCtr));
            System.out.println(game.color(YELLOW) + game.guess);
            System.out.println(game.color(GREEN)+"Correct! I'm impressed"+game.color(WHITE));
            isABigBoi = true;
            System.out.print("Time: " + game.color(BLUE));
            timer.printSimplifiedTime(timer.getTimeElapsed());
          }else{
            guessCtr+=1;
            phraseGuess.clear();
            if(guessCtr == 6){
              clear();
              System.out.println(hangman.returnDrawing(guessCtr));
              timer.stopTimer();
              timer.printSimplifiedTime(timer.getTimeElapsed());
              System.out.println("You have run out of guesses :(");
              System.out.println("Better luck next time!");
              System.out.println("The phrase was " + game.guess);
              return;
            }
            System.out.println(hangman.returnDrawing(guessCtr));
            clear();
            System.out.println(hangman.returnDrawing(guessCtr));
            System.out.println(game.returnColoredCurrent());
            System.out.println("Incorrect...");
            System.out.println("Incorrect Guesses: " + game.colorWrongGuesses(wrongGuesses));
            System.out.print("Would you like to guess the entire phrase? ("+game.color(GREEN)+"Y/"+game.color(RED)+"N"+game.color(WHITE)+"): ");
            continue;
          }
        }else if((f.toLowerCase().equals("n"))||(f.toLowerCase().equals("no"))){
          clear();
          System.out.println(hangman.returnDrawing(guessCtr));
          System.out.println(game.returnColoredCurrent());
          System.out.println("Incorrect Guesses: " + game.colorWrongGuesses(wrongGuesses));
        }else{
          System.out.println("We'll take that as a no");
        }
        break;
      }

      if(isABigBoi){ // what does this do?
        break;
      }

      boolean test = false; // Init to see if user has guessed a letter already
      System.out.print("Enter your guess: ");
      Scanner guess = new Scanner(System.in);

      while(guess.hasNextLine()){
        q = guess.next().charAt(0);
        p = game.find(q);

        if ( (game.returnArr(wrongGuesses).indexOf(q) > -1) || (game.returnColoredCurrent().indexOf(q) > -1) ) {
          test = true;
        }
        else if (p.size()==0){
          guessCtr += 1;
          wrongGuesses.add(q);
          clear();
          System.out.println(hangman.returnDrawing(guessCtr));
          System.out.println("Incorrect...");
          System.out.println(game.returnColoredCurrent());
          System.out.println("Incorrect Guesses: " + game.colorWrongGuesses(wrongGuesses));
          break;
        }

        for(int e : p){
          game.current.set(e, game.guessArr.get(e));
        }

        clear();
        System.out.println(hangman.returnDrawing(guessCtr));
        System.out.println(game.returnColoredCurrent());
        if (test == true) {
          System.out.println("You have already guessed this letter!");
          System.out.println("Don't think we wouldn't catch you slippin'!");
          System.out.println("Please try again : ^ )");
          test = false;
        }
        System.out.println("Incorrect Guesses: " + game.colorWrongGuesses(wrongGuesses));
        break;
      }
  }
  if(game.guessArr.equals(game.current)){
    timer.stopTimer();
    clear();
    System.out.println(game.returnDrawing(guessCtr));
    System.out.println(game.color(YELLOW) + game.guess);
    System.out.println(game.color(GREEN)+"Correct! I'm impressed"+game.color(WHITE));
    System.out.print("Time: " + game.color(BLUE));
    timer.printSimplifiedTime(timer.getTimeElapsed());
  }
  System.out.println(SHOW_CURSOR); // don't want to get sued for making the user's cursor disappear

  } // end of main method
} // end of class
