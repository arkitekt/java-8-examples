package chapter2.filter;

import chapter2.Apple;

/**
 * @author Olavi
 */
public class AppleGreenColorPredicate implements ApplePredicate {

  @Override
  public boolean test(Apple apple) {
    return "green".equalsIgnoreCase(apple.getColor());
  }
}
