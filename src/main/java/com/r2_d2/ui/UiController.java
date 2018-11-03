package com.r2_d2.ui;

import com.r2_d2.parser.arithmetic_string_parsing.ArithmeticStringParsing;

import java.util.Scanner;

/**
 * UiController class implements interconnection with user by sending him messages.
 *
 * Gives different options for solving entered expression
 */
public class UiController {
  public void printMsg(String msg){
    System.out.print(msg);
  }

  public void printMainMenu(){
    printMsg("***Action menu***\n");
    printMsg("1 - to solve expression\n");
    printMsg("0 - to exit\n");
    printMsg("Press action: ");
  }

  /**
   * Get int value from user
   *
   * @param scanner   what reads user input
   * @return          return entered value
   */
  private int getValue(Scanner scanner){
    int tmp = scanner.nextInt();
    return tmp;
  }

  /**
   * Prints messages, show results of user's actions
   *
   * @param scanner   what reads user input
   * @param parser    what reads user expression and gives result of this expression
   */
  public void runUi(ArithmeticStringParsing parser , Scanner scanner){
    boolean runApp = true;
    int choice = -1;

    while(runApp){
      printMainMenu();
      choice=getValue(scanner);

      printMsg("\n");

      int tmp;
      switch (choice){
        case 1:
          printMsg("Expression: ");

          scanner.nextLine();
          String expression = scanner.nextLine();
          parser = new ArithmeticStringParsing();
          System.out.println("\nResult: " + parser.calculateExpression(expression));
          System.out.println();

          parser=null;
          break;
        case 0:
          runApp = false;
          break;
        default:
          printMsg("Not such option!!!\n\n");
      }
    }
  }
}
