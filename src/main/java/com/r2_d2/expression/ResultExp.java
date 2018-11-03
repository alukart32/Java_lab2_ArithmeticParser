package com.r2_d2.expression;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ProcessingExp is a class implements original user expression in Double
 * It calculates expression
 *
 * Parameter "elements"    elements of user expression
 */
public class ResultExp {
  // Deque of numbers which will get final result by applying operation {+, -, *, /}
  private Deque<Double> elements;

  /**
   * Constructor created "elements"
   */
  public ResultExp() {
    elements = new ArrayDeque<>();
  }

  /**
   * Adding new Double value
   * @param data
   */
  public void add(double data){elements.add(data); }

  /**
   * Getting last item from "elements"
   * @return  String item
   */
  public double pollLast(){
    return elements.pollLast();
  }


  /**
   * Getting first item from "elements"
   * @return  String item
   */
  public  double pollFirst(){
    return elements.pollFirst();
  }

  /**
   * Checking on emptiness of "elements"
   * @return
   */
  public  boolean isEmpty(){
    return  elements.isEmpty();
  }
}
