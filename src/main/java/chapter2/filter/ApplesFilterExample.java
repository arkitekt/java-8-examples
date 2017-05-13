package chapter2.filter;

import chapter2.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Olavi
 */
public class ApplesFilterExample {

  public List<Apple> filter(List<Apple> inventory, ApplePredicate predicate) {
    List<Apple> result = new ArrayList<>();

    for (Apple apple : inventory) {
      if (predicate.test(apple)) {
        result.add(apple);
      }
    }

    return result;
  }

}
