public class Timer{
  // A class to assist us keep track of how long the user played the game.
  private long timeElapsed;
  public long getTimeElapsed(){
    return timeElapsed;
  }
  public void startTimer(){
    timeElapsed = System.currentTimeMillis();
  }
  public void stopTimer(){
    timeElapsed = System.currentTimeMillis() - timeElapsed;
  }
  public void printSimplifiedTime(long timeElapsed){
    int secondsElapsed = ((int) timeElapsed) / 1000;
    int minutesElapsed = secondsElapsed / 60;
    System.out.print(minutesElapsed + " minutes " + secondsElapsed%60 + " seconds.");
    // We assume that the user will not take more than an 59 minutes to complete the code.
    // Thus, the simplied time for our code will tell the user the number of minutes and number of seconds that have elapsed.
    // We will not simplify past the hour mark.
  }
}
