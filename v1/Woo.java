// AliensArrivingNaughtily -- Ameer Alnasser + Nakib Abedin + Alif Rahman
// APCS pd06
// FP - (Don't) Hang da Man
// 2022-01-18t
// time spent: 2 hrs

import java.io.*;
import java.util.*;

public class Woo {
  static List<String> wordBank = new ArrayList<String>();
  List<Character> guessArr = new ArrayList<Character>();
   List<Character> current = new ArrayList<Character>();
  public String addGuess(){
    int x = (int) (Math.random()*wordBank.size());
    String guess=wordBank.get(x);
    guess=guess.toUpperCase();

   for(char c:guess.toCharArray()){
     guessArr.add(c);
     if(c<=90 &&c>=65){
       current.add('_');
     }
     else{
       current.add(c);
     }
     current.add(' ');
     guessArr.add(' ');
   }
   String output="";
   for(char e: current){
     output+=e;
   }
   return output;
  }
  public static void populate(String fileName){
    // populates arrayList WordBankwith inputs from fileName
    try{
      Scanner inputs = new Scanner(new File(fileName));
      while(inputs.hasNextLine()){
        String item = inputs.nextLine();
        wordBank.add(item);
      }
    }catch (Exception IOException){
      System.out.println("input file does not exist within this directory");
    }
  }

  public static void main(String[] args){

    System.out.println("HANGMAN !!!");
    System.out.println("Select your category: ");
    System.out.println("0. Places \n1. Celebrities \n2. Movies \n3. Tofr's Wise Words \n4. Period 6 Thinkeren \n5. Quotes \n6. DIY");

    Scanner sc = new Scanner(System.in);
    Woo game= new Woo();
    int category = sc.nextInt();
    switch(category){ //Inspired by Stuy alumnus and current Cornell TA, aka Ameer's brother
      case 0:
          populate("Places.in");
          break;
      case 1:
          populate("Celebrities.in");
          break;
      case 2:
          populate("Movies.in");
          break;
      case 3:
          populate("TofrsWords.in");
          break;
      case 4:
          populate("Thinkeren.in");
          break;
      case 5:
          populate("Quotes.in");
          break;
      case 6:
          System.out.println("Alif likes chicken n");
          break;
        }
        System.out.println(game.addGuess());
            System.out.println(game.current);

  } // end of main method
} // end of class
