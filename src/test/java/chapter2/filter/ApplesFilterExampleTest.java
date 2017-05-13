package chapter2.filter;

import chapter2.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Olavi
 */
public class ApplesFilterExampleTest {

  private ApplesFilterExample example;

  @Before
  public void setUp() {
    example = new ApplesFilterExample();
  }

  @Test
  public void testHeavyApples() throws Exception {
    List<Apple> apples = new ArrayList<>();
    apples.add(Apple.create("green", 120));
    apples.add(Apple.create("green", 160));
    apples.add(Apple.create("green", 110));
    apples.add(Apple.create("green", 180));

    List<Apple> result = example.filter(apples, new AppleHeavyWeightPredicate());

    assertFalse(result.isEmpty());
    assertEquals(2, result.size());

    assertEquals(160, result.get(0).getWeight());
    assertEquals(180, result.get(1).getWeight());
  }

  @Test
  public void testGreenApples() throws Exception {
    List<Apple> apples = new ArrayList<>();
    apples.add(Apple.create("green", 110));
    apples.add(Apple.create("red", 110));
    apples.add(Apple.create("yellow", 110));
    apples.add(Apple.create("green", 110));

    List<Apple> result = example.filter(apples, new AppleGreenColorPredicate());

    assertFalse(result.isEmpty());
    assertEquals(2, result.size());

    assertEquals("green", result.get(0).getColor());
  }
}