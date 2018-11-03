package com.r2_d2;

import com.r2_d2.parser.arithmetic_string_parsing.ArithmeticStringParsing;
import com.r2_d2.ui.UiController;

import java.util.Scanner;

/**
 * Main start point of application.
 * Create Scanner, UiController which
 * communicates with user
 */
public class Main{
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    ArithmeticStringParsing parser = new ArithmeticStringParsing();
    UiController ui = new UiController();

    ui.runUi(parser, scanner);

  }
}
