package com.r2_d2.parser.arithmetic_string_parsing;

import com.r2_d2.expression.ProcessingExp;
import com.r2_d2.expression.ResultExp;
import com.r2_d2.operation.Operation;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * ArithmeticStringParsing class implements solving user expression.
 * First, it turns expression into Polish revers notation.
 * Then it calculates expression value
 * <p>
 *
 * Parameters:
 *              ProcessingExp     Polish revers notation
 *              ResultExp         Double value of user expression
 *              tokenDequeue      Stack of expression's tokens{+,-,*,/}
 *              currentChar       current processing char elements of user expression
 */
public class ArithmeticStringParsing {
  // Deque of numbers which will get final result by applying operation {+, -, *, /}
  private ProcessingExp processedExp;
  private ResultExp resultStack;

  // queue of tokens which were met in an expression
  private Deque<Character> tokenDequeue;
  private int currentChar = 0;

  public ArithmeticStringParsing() {
    this.resultStack = new ResultExp();
    this.processedExp = new ProcessingExp();
    this.tokenDequeue = new ArrayDeque<>();
  }

  public double calculateExpression(String expression) {
    // обработка выражения в обратную польскую запись
    processingExpression(expression);

    int i = 0;
    double d1;
    double d2;

    String tmp;
    while (!this.processedExp.isEmpty()) {
      tmp = this.processedExp.pollFirst();
      if (tmp.charAt(0) != '+' && tmp.charAt(0) != '-' && tmp.charAt(0) != '*' && tmp.charAt(0) != '/') {
        this.resultStack.add(getValue(tmp));
      } else {
        // добавить результат
        d2 = this.resultStack.pollLast();
        d1 = this.resultStack.pollLast();
        this.resultStack.add(calculate(tmp, d1, d2));
      }
    }

    return this.resultStack.pollLast();
  }

  /**
   * Turning user expression into Polish revers notation
   *
   * Parameters:
   *        firstOperandTypeOne  first met operator + or -
   *        firstOperandTypeTwo  first met operator * or /
   * @param expression    what will be processed
   */
  private void processingExpression(String expression) {
    // {+, -}
    boolean firstOperandTypeOne = true;
    // {*, /}
    boolean firstOperandTypeTwo = true;

    tokenDequeue = new ArrayDeque<>();
    processedExp = new ProcessingExp();

    currentChar = 0;

    while (currentChar < expression.length()) {
      if (expression.charAt(currentChar) >= '0' && expression.charAt(currentChar) <= '9') {
        processedExp.add(getOperand(expression));
      }

      if (expression.charAt(this.currentChar) == '+' || expression.charAt(this.currentChar) == '-') {
        if (!this.tokenDequeue.isEmpty())
          if (this.tokenDequeue.getLast() == '*' || this.tokenDequeue.getLast() == '/'){
            while(!this.tokenDequeue.isEmpty()) {
              this.processedExp.add(String.valueOf(tokenDequeue.pollLast()));
            }
            firstOperandTypeTwo = true;
          }
        if(firstOperandTypeOne){
          this.tokenDequeue.add(expression.charAt(this.currentChar));
          firstOperandTypeOne = false;
        }else{
          while(!this.tokenDequeue.isEmpty()) {
            this.processedExp.add(String.valueOf(tokenDequeue.pollLast()));
          }

          this.tokenDequeue.add(expression.charAt(this.currentChar));
          firstOperandTypeOne = true;
        }

      }

      if (expression.charAt(this.currentChar) == '*' || expression.charAt(this.currentChar) == '/'){
        // проверка на то, что было ли уже *|/ , чтобы выталкнуть его в resultStack и новый *|/ занести за место старого
        if (firstOperandTypeTwo){
          this.tokenDequeue.add(expression.charAt(this.currentChar));
          firstOperandTypeTwo = false;
        }else {
          this.processedExp.add(String.valueOf(tokenDequeue.pollLast()));
          this.tokenDequeue.add(expression.charAt(this.currentChar));
          firstOperandTypeTwo = true;
        }
      }

      currentChar++;
    }
    while(!this.tokenDequeue.isEmpty())  {
    this.processedExp.add(String.valueOf(tokenDequeue.pollLast()));
    }
}

  /**
   * Calculate operandOne operator operandTwo
   *
   * @param operation   operator
   * @param d1          operandOne
   * @param d2          operandTwo
   * @return
   */
  private double calcOperation(String operation, double d1, double d2){
    double result = 0;

    switch (operation){
      case "+":
        result = Operation.add(d1,d2);
        break;
      case "-":
        result = Operation.sub(d1,d2);
        break;
      case "*":
        result = Operation.mult(d1,d2);
        break;
      case "/":
        // exception: d2 != 0
        if (d2 == 0) {
          throw new IllegalArgumentException("Argument 'divisor' is 0");
        }
        result = Operation.div(d1,d2);
        break;
    }
    return result;
  }

  /**
   * Getting operand as String from  char sequence
   *
   * @param target    from what will be got operand
   * @return  tmp     this operand
   */
  private String getOperand(String target){
    String tmp = new String("");
    while(this.currentChar != target.length() &&
            (target.charAt(this.currentChar) >= '0' && target.charAt(this.currentChar) <= '9')){
      tmp = tmp + target.charAt(this.currentChar);
      this.currentChar++;
    }
    this.currentChar--;
    return  tmp;
    //return  getValue(tmp);
  }

  /**
   * Getting value of String operand
   *
   * @param target    from which operand
   * @return result   Double value
   */
  private double getValue(String target){
  int k = 1;
  double result = 0;

    for (int i = target.length()-1; i >= 0 ; i--) {
      result+=k*Character.getNumericValue(target.charAt(i));
      k*=10;
    }

  return result;
 }

  /**
   * Call calcOperation
   *
   * @param operation    {+,-,*,/}
   * @param d1          operandOne
   * @param d2          operandTwo
   * @return            Double value
   */
 private double calculate(String operation, double d1, double d2){
   return calcOperation(operation, d1, d2);
 }
}

