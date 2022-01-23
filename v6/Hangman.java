public class Hangman {
  private static final String stage0 = "____________\n|/   | \n|    |\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|___________";
  private static final String stage1 = "____________\n|/   | \n|    |\n|   (_)\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|___________";
  private static final String stage2 = "____________\n|/   | \n|    |\n|   (_)\n|    | \n|    |\n|    |\n|    |\n|\n|\n|\n|\n|\n|___________";
  private static final String stage3 = "\u001b[33m____________\n|/   | \n|    |\n|   (_)\n|  \\ | \n|   \\|\n|    |\n|    |\n|\n|\n|\n|\n|\n|___________\u001b[37m";
  private static final String stage4 = "\u001b[33m____________\n|/   | \n|    |\n|   (_)\n|  \\ | /\n|   \\|/\n|    |\n|    |\n|\n|\n|\n|\n|\n|___________\u001b[37m";
  private static final String stage5 = "\u001b[31m____________\n|/   | \n|    |\n|   (_)\n|  \\ | /\n|   \\|/\n|    |\n|    |\n|   / \n|  /   \n|\n|\n|\n|___________\u001b[37m";
  private static final String stage6 = "\u001b[31m____________\n|/   | \n|    |\n|   (_)\n|  \\ | /\n|   \\|/\n|    |\n|    |\n|   / \\\n|  /   \\\n|\n|\n|\n|___________\u001b[37m";

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
