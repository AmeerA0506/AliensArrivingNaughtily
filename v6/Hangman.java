// AliensArrivingNaughtily -- Ameer Alnasser + Nakib Abedin + Alif Rahman
// APCS pd06
// FP - (Don't) Hang da Man
// 2022-01-24m
// time spent: 25 hrs

public class Hangman {
// Parent class of Game.java
// Contains all the stages of Hangman along with the ANSI values that will be inherited by Game.java

// Different Stages of Hangman
  private static final String stage0 = "____________\n|/   | \n|    |\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|___________";
  private static final String stage1 = "____________\n|/   | \n|    |\n|   (_)\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|___________";
  private static final String stage2 = "____________\n|/   | \n|    |\n|   (_)\n|    | \n|    |\n|    |\n|    |\n|\n|\n|\n|\n|\n|___________";
  private static final String stage3 = "\u001b[33m____________\n|/   | \n|    |\n|   (_)\n|  \\ | \n|   \\|\n|    |\n|    |\n|\n|\n|\n|\n|\n|___________\u001b[37m";
  private static final String stage4 = "\u001b[33m____________\n|/   | \n|    |\n|   (_)\n|  \\ | /\n|   \\|/\n|    |\n|    |\n|\n|\n|\n|\n|\n|___________\u001b[37m";
  private static final String stage5 = "\u001b[31m____________\n|/   | \n|    |\n|   (_)\n|  \\ | /\n|   \\|/\n|    |\n|    |\n|   / \n|  /   \n|\n|\n|\n|___________\u001b[37m";
  private static final String stage6 = "\u001b[31m____________\n|/   | \n|    |\n|   (_)\n|  \\ | /\n|   \\|/\n|    |\n|    |\n|   / \\\n|  /   \\\n|\n|\n|\n|___________\u001b[37m";
// All copied and pasted from ap251/library/TerminallyIll.java
  // Thank you Mr. K!

// terminal hax
  protected static final int RED = 31;
  protected static final int GREEN = 32;
  protected static final int YELLOW = 33;
  protected static final int BLUE = 34;
  protected static final int CYAN = 36;
  protected static final int WHITE = 37;
  protected static final String CLEAR_SCREEN =  "\033[2J";
  protected static final String HIDE_CURSOR =  "\033[?25l";
  protected static final String SHOW_CURSOR =  "\033[?25h";

  public String returnDrawing (int x) {
    String answer = "";
    switch(x){
      case 0:
          answer = stage0;
          break;
      case 1:
          answer = stage1;
          break;
      case 2:
          answer = stage2;
          break;
      case 3:
          answer = stage3;
          break;
      case 4:
          answer = stage4;
          break;
      case 5:
          answer = stage5;
          break;
      case 6:
          answer = stage6;
          break;
        }
      return answer + "\n";
  }

} // end of class
