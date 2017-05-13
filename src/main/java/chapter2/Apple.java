package chapter2;

/**
 * @author Olavi
 */
public class Apple {

  private String color;
  private int weight;

  private Apple() {
  }

  public String getColor() {
    return color;
  }

  public int getWeight() {
    return weight;
  }

  public static Apple create(String color, int weight) {
    Apple apple = new Apple();

    apple.color = color;
    apple.weight = weight;

    return apple;
  }
}
