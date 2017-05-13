package chapter2.formatter;

import chapter2.Apple;

import java.util.List;

/**
 * @author Olavi
 */
public class ApplesPrintingExample {

  public void prettyPrintApples(List<Apple> inventory, AppleFormatter formatter) {
    for (Apple apple : inventory) {
      System.out.println(formatter.accept(apple));
    }
  }
}
