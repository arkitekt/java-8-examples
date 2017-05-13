package chapter2.formatter;

import chapter2.Apple;

/**
 * @author Olavi
 */
public class AppleSimpleFormatter implements AppleFormatter {

  public String accept(Apple apple) {
    return String.format("An apple of %s g", apple.getWeight());
  }
}
