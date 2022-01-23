// AliensArrivingNaughtily -- Ameer Alnasser + Nakib Abedin + Alif Rahman
// APCS pd06
// FP - (Don't) Hang da Man
// 2022-01-24m
// time spent: 25 hrs

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Game extends Hangman{

  static List<String> wordBank = new ArrayList<String>(); 
  String guess; 
  List<Character> guessArr = new ArrayList<Character>();
  List<Character> current = new ArrayList<Character>();

  public String color(int x){
    // will set the color of a String to the desired input
    // used to improve readability in Woo.java
    String output = "";
    output += "\u001B[" + x + "m";
    return output;
  }

  private static void wait(int millis)
  // Taken from TerminallyIll.java
  {
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
    }
  }

  public void initialScreen(){
    // will print out the loading screen
    List<String> initScreen = new ArrayList<>();
    initScreen.add("\u001B["+WHITE+ "m_____________________________________________________________________________");
    initScreen.add("\n| /  \u001B["+RED+ "m |                                                                     \u001B["+WHITE+"m|");
    initScreen.add("\n|/   \u001B["+RED+ "m |                       \u001B["+YELLOW+"m,--.           |    \u001B["+WHITE+"m                          |");
    initScreen.add("\n|   \u001B["+RED+ "m (_)                      \u001B["+YELLOW+"m|   |,---.,---.|--- \u001B["+WHITE+"m                          |");
    initScreen.add("\n|  \u001B["+RED+ "m \\ | /                    \u001B["+YELLOW+"m |   ||   ||   ||   \u001B["+WHITE+"m                           |");
    initScreen.add("\n|   \u001B["+RED+ "m \\|/                     \u001B["+YELLOW+"m `--' `---'`   '`---\u001B["+WHITE+"m                           |");
    initScreen.add("\n|    \u001B["+RED+ "m |        \u001B["+GREEN+"m|   |                 \u001B["+CYAN+"m  ,--.        \u001B["+BLUE+"m  ,-.-.              \u001B["+WHITE+"m    |");
    initScreen.add("\n|    \u001B["+RED+ "m |        \u001B["+GREEN+"m|---|,---.,---.,---.  \u001B["+CYAN+"m  |   |,---.  \u001B["+BLUE+"m  | | |,---.,---.    \u001B["+WHITE+"m    |");
    initScreen.add("\n|   \u001B["+RED+ "m / \\      \u001B["+GREEN+"m |   |,---||   ||   | \u001B["+CYAN+"m   |   |,---| \u001B["+BLUE+"m   | | |,---||   |   \u001B["+WHITE+"m     |");
    initScreen.add("\n|  \u001B["+RED+ "m /   \\     \u001B["+GREEN+"m `   '`---^`   '`---| \u001B["+CYAN+"m   `--' `---^ \u001B["+BLUE+"m   ` ' '`---''   '   \u001B["+WHITE+"m     |");
    initScreen.add("\n|                            \u001B["+GREEN+"m `---'   \u001B["+WHITE+ "m                                      |");
    initScreen.add("\n|                             Press \u001B["+RED+"mEnter\u001B["+WHITE+"m to Start                          |\n|___________________________________________________________________________|");

    for(String line : initScreen){
      // a loading animation because all great games have a loading screen :)
      // plus we think it looks cool
      System.out.print(line);
      wait(100);
    }
  }

    public String addGuess(){
      // will add the word being guessed to ArrayList current with all of the letters being replaced with a dash
      int x = (int) (Math.random()*wordBank.size());
      guess=wordBank.get(x);
      String output="";

      for(char c:guess.toUpperCase().toCharArray()){
         guessArr.add(c);
         if(c<=90 && c>=65){
           current.add('_');
         }
         else{
           current.add(c);
         }
         current.add(' ');
         guessArr.add(' ');
     }

     for(char e: current){
       output+=e;
     }

     return output;
    }

    public void populate(String fileName){
      // populates arrayList WordBank with inputs from path/to/fileName
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

    public ArrayList<Integer> find(Character target){
      // finds all instances of target in the word being guessed and returns a integer ArrayList with all of the indices
      ArrayList<Integer> indices = new ArrayList<Integer>(); // will store indicies
      for(int i = 0; i < guessArr.size(); i++){
        if(guessArr.get(i).equals(target)||guessArr.get(i).equals(Character.toUpperCase(target))){
          indices.add(i);
        }
      }
      return indices;
    }

    public String returnArr(List<Character> input){
      // Will return the String version of an ArrayList 
      String output = "";
      for(char c : input){
        output += c;
      }
      return output;
    }

    public void clearArray (List<Character> arr){
      // will clear all items in an ArrayList
      for (int i=0; i<arr.size(); i++){
        arr.remove(i);
        arr.remove(" ");
      }
      System.out.println(arr);
    }

    public String returnColoredCurrent(){
      // specialized method to return a colored version of Current with the intended format.
      String currentString = "";
      String output = "";
      for(char c : current){
        currentString += c;
      }
      for(int i = 0; i<currentString.length(); i++){
        if(currentString.charAt(i) == 95){
          output = output + color(WHITE) + currentString.charAt(i);
        }else{
          output = output + color(GREEN) + currentString.charAt(i);
        }
        }
      return output + color(WHITE);
    }

    public String colorWrongGuesses(List<Character> arr){
      // specialized method to return a colorized version of List<Character> wrongGuesses from Woo.java
      String stringify = "";
      String output = "";
      if(arr.size()==0){
        return output;
      }
      for(char c : arr){
        stringify = stringify + c + " ";
      }
      for(int i = 0; i<stringify.length(); i++){
        output = output + color(RED) + stringify.charAt(i);
      }
      return output + color(WHITE);
    }

}// end class
