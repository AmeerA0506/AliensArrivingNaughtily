// AliensArrivingNaughtily -- Ameer Alnasser + Nakib Abedin + Alif Rahman
// APCS pd06
// FP - (Don't) Hang da Man
// 2022-01-18t
// time spent: 2 hrs

import java.io.*;
import java.util.*;

public class Woo {

  public static void main(String[] args) {
    Scanner arrayPopulation = new Scanner(new File("Places.in"));
    Scanner sc = new Scanner(System.in);
    ArrayList<String> wordBank = new ArrayList<String>();
    while (arrayPopulation.hasNextLine()) {
      wordBank.add(arrayPopulation.next());
    }
    System.out.println("HANGMAN !!!");
    System.out.println("Select your category: ");
    System.out.println("0. Places \n1. Celebrities \n2. Movies \n3. Tofr's Wise Words \n4. Period 6 Thinkeren \n5. Quotes \n6. DIY");

    // try {
    //  int category = Integer.parseInt( in.readLine() );
    // }
    // catch ( IOException e ) { }
    //
    // if (category == 0) {
    //
    // }
    // else if (category == 1) {
    //
    // }
    // else if (category == 2) {
    //
    // }
    // else if (category == 3) {
    //
    // }
    // else if (category == 4) {
    //
    // }
    // else if (category == 5) {
    //
    // }
    // else {
    //
    // }

  } // end of main method
} // end of class
