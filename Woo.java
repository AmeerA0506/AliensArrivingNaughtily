// AliensArrivingNaughtily -- Ameer Alnasser + Nakib Abedin + Alif Rahman
// APCS pd06
// FP - (Don't) Hang da Man
// 2022-01-18t
// time spent: 5 hrs

import java.io.*;
import java.util.*;

public class Woo {

  public static void main(String[] args){
    Game game= new Game();
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
