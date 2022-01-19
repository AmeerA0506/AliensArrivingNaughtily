import java.io.*;
import java.util.*;

public class Game {

  static List<String> wordBank = new ArrayList<String>();
  List<Character> guessArr = new ArrayList<Character>();
  List<Character> current = new ArrayList<Character>();

  public static void initialScreen(){
    String initialScreen = "";
    initialScreen += "_______";
    initialScreen += "\n|/  |         ,--.           |";
    initialScreen += "\n|  (_)        |   |,---.,---.|---";
    initialScreen += "\n|  \\|/        |   ||   ||   ||";
    initialScreen += "\n|   |         `--' `---'`   '`---";
    initialScreen += "\n|   |         |   |                   ,--.          ,-.-.";
    initialScreen += "\n|   |         |---|,---.,---.,---.    |   |,---.    | | |,---.,---.";
    initialScreen += "\n|  / \\        |   |,---||   ||   |    |   |,---|    | | |,---||   |";
    initialScreen += "\n|             `   '`---^`   '`---|    `--' `---^    ` ' '`---''   '";
    initialScreen += "\n|___|___                     `---'";
    System.out.println(initialScreen);

  }

    public String addGuess(){
      int x = (int) (Math.random()*wordBank.size());
      String guess=wordBank.get(x);
      guess=guess.toUpperCase();
      String output="";

      for(char c:guess.toCharArray()){
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

    public ArrayList<Integer> find(Character target){
      ArrayList<Integer> indicies = new ArrayList<Integer>(); // will store indicies
      for(int i = 0; i < guessArr.size(); i++){
        if(guessArr.get(i).equals(target)){
          indicies.add(i);
        }
      }
      return indicies;
    }

    public String printArr(List<Character> input){
      String output = "";
      for(char c : input){
        output += c;
      }
      return output;
    }
}
