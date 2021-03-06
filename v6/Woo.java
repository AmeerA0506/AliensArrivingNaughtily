// AliensArrivingNaughtily -- Ameer Alnasser + Nakib Abedin + Alif Rahman
// APCS pd06
// FP - (Don't) Hang da Man
// 2022-01-24m
// time spent: 25 hrs

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Woo {

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
  // Thank you SK and TM

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

    if (enter.hasNextLine()){
      clear();
      System.out.println(game.color(YELLOW)+"Welcome"+game.color(WHITE)+" to "+game.color(RED)+"DON'T"+game.color(WHITE)+" Hang da Man!");
      System.out.println("This project was created by "+game.color(GREEN)+"Nakib Abedin, "+game.color(BLUE)+"Ameer Alnasser, "+game.color(WHITE)+"and"+game.color(MAGENTA)+" Alif Rahman"+game.color(WHITE));
      System.out.println("Before we get started, you should know how this game works: \n");
      System.out.println("0. There are 3 sets of difficulties. After a few incorrect guesses, you will get a hint telling you the theme.");
      System.out.println("1. There is also a customizable mode in which you can input a variety of phrases. The word that you will be guessing will be randomly selected from these inputs.");
      System.out.println("2. For any input you make, we will use the first character.");
      System.out.println("3. You are not allowed to guess one character more than once.");
      System.out.println("4. After six incorrect guesses, the game will end.");
      System.out.println("5. You will see the amount of time you spent on the game at the end of the program.");
      System.out.println(game.color(GREEN)+"\nGreat! "+game.color(WHITE)+"Now that we know how it works, let's get"+game.color(YELLOW)+" playin'"+game.color(WHITE));

      System.out.println("Press "+game.color(CYAN)+"Enter"+game.color(WHITE)+" to continue");
    }

    Scanner startPlaying = new Scanner(System.in);

    if(startPlaying.hasNextLine()){
      clear();
      System.out.println(game.color(GREEN) + "0-1: Easy");
      System.out.println(game.color(YELLOW)+"2-3: Medium");
      System.out.println(game.color(RED)+"4-5: Difficult");
      System.out.println(game.color(CYAN)+"6: Custom");
      System.out.print(game.color(WHITE)+"Select your difficulty: ");
    }

    Scanner sc = new Scanner(System.in);
    String hint="";
    int category = sc.nextInt();
    
    switch(category){ //Inspired by Stuy alumnus and current Cornell TA, aka Ameer's brother
      case 0:
          game.populate("inputs/StuyAndAround.in");
          hint="Places near or in Stuyvesant";
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
    String f=""; //used to populate phraseGuess if the user chooses to guess the entire phrase
    List<Character> phraseGuess = new ArrayList<Character>(); // this will store the phrase guessed by the user
    boolean isABigBoi = false; // this is used to break out of the while loop if they 

    while(! (game.guessArr.equals(game.current)) ){
      if(guessCtr==6){
        // end the game
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
      if(guessCtr >= 3 && category != 6){
        // give a hint
        System.out.println("\nLooks like you are a little stuck. Here is a hint:");
        System.out.println("The phrase has something to do with " + game.color(CYAN)+ hint + game.color(WHITE) + "\n");
      }

      System.out.print("Would you like to guess the entire phrase? ("+game.color(GREEN)+"Y/"+game.color(RED)+"N"+game.color(WHITE)+"): ");
      Scanner bigBoiGuess = new Scanner(System.in);//scanner used to determine if the user wants to guess the whole thing
      // give option to gues the whole thing

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
            System.out.println("Wrong...");
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
          System.out.println("Wrong...");
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
