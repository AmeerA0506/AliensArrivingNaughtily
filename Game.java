import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Game extends Hangman{

  // terminal hax
  private static final int BRIGHT = 1;
  private static final int DARK = 2;
  private static final int ITALICS = 3;
  private static final int BLACK = 30;
  private static final int BLUE = 34;
  private static final int GREEN = 32;
  private static final int RED = 31;
  private static final int YELLOW = 33;
  private static final int MAGENTA = 35;
  private static final int CYAN = 36;
  private static final int WHITE = 37;
  private static final String CLEAR_SCREEN =  "\033[2J";
  private static final String HIDE_CURSOR =  "\033[?25l";
  private static final String SHOW_CURSOR =  "\033[?25h";

  public static List<String> wordBank = new ArrayList<String>();
  public List<Character> guessArr = new ArrayList<Character>();
  public List<Character> current = new ArrayList<Character>();

  private static void wait(int millis)
  {
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
    }
  }

  public void initialScreen(){
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
    initScreen.add("\n|                             Press \u001B["+RED+"mEnter\u001B["+WHITE+"m to Start                          |");
    initScreen.add("\n|___________________________________________________________________________|");

    for(String line : initScreen){
      System.out.print(line);
      wait(100);
    }
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
        if(guessArr.get(i).equals(target)||guessArr.get(i).equals(Character.toUpperCase(target))){
          indicies.add(i);
        }
      }
      return indicies;
    }

    public String returnArr(List<Character> input){
      String output = "";
      for(char c : input){
        output += c;
      }
      return output;
    }

    public String returnWrongGuesses(List<Character> inputArr){
      String output = "";
      if(inputArr.size()==0){
        return output;
      }
      for (Character c : inputArr){
        output = output + c + " ";
      }
      return output;
    }

    public void clearArray (List<Character> arr){
      for (int i=0; i<arr.size(); i++){
        arr.remove(i);
        arr.remove(" ");
      }
      System.out.println(arr);
    }

}// end class
