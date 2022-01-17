// AliensArrivingNaughtily -- Ameer Alnasser + Nakib Abedin + Alif Rahman
// APCS pd06
// FP - (Don't) Hang da Man
// 2022-01-18t
// time spent: 2 hrs

import java.io.*;
import java.util.*;

public class Woo {

  static ArrayList<String> wordBank = new ArrayList<String>();

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
    int category = sc.nextInt();

    if (category == 0) {
      populate("Places.in");
    }
    else if (category == 1) {
      populate("Celebrities.in");
    }
    else if (category == 2) {
      populate("Movies.in");
    }
    else if (category == 3) {
      populate("TofrsWords.in");
    }
    else if (category == 4) {
      populate("Thinkeren.in");
    }
    else if (category == 5) {
      populate("Quotes.in");
    }
    else {
      System.out.println("Alif likes chicken nuggets");
    }

  } // end of main method
} // end of class
