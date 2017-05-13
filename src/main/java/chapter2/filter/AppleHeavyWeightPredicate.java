package chapter2.filter;

import chapter2.Apple;

/**
 * @author Olavi
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {

  @Override
  public boolean test(Apple apple) {
    return apple.getWeight() > 150;
  }
}
