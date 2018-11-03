package com.r2_d2.expression;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ProcessingExp is a class implements that original user expression
 * which was turned in Polish  revers notation
 *
 * Parameter elements    elements of modified user expression
 */
public class ProcessingExp {
  // Deque of numbers which will get final result by applying operation {+, -, *, /}
  private Deque<String> elements;

  /**
   * Constructor created elements of Polish revers notation
   */
  public ProcessingExp() {
    elements = new ArrayDeque<>();
  }

  public void add(String data){
    elements.add(data);
  }

  /**
   * Getting last item from "elements"
   * @return  String item
   */
  public String pollLast(){
    return elements.pollLast();
  }

  /**
   * Getting first item from "elements"
   * @return
   */
  public String pollFirst(){
    return elements.pollFirst();
  }

  /**
   * Checking on emptiness of "elements"
   * @return
   */
  public boolean isEmpty(){
    return  elements.isEmpty();
  }
}
