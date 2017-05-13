package chapter2.formatter;

import chapter2.Apple;

/**
 * @author Olavi
 */
public class AppleFancyFormatter implements AppleFormatter {

  public String accept(Apple apple) {
    return String.format("A %s %s apple", apple.getWeight() > 150 ? "heavy" : "light", apple.getColor());
  }
}
