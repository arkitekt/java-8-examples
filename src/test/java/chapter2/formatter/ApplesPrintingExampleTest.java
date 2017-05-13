package chapter2.formatter;

import chapter2.Apple;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Olavi
 */
public class ApplesPrintingExampleTest {

  private ApplesPrintingExample example;
  private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outputStream));
    example = new ApplesPrintingExample();
  }

  @After
  public void tearDown() {
    System.setOut(null);
  }

  @Test
  public void testFancyResult() {
    example.prettyPrintApples(createInventory(), new AppleFancyFormatter());

    assertEquals("A light green apple\nA heavy red apple\n", outputStream.toString());
  }

  @Test
  public void testSimpleResult() throws Exception {
    example.prettyPrintApples(createInventory(), new AppleSimpleFormatter());

    assertEquals("An apple of 120 g\nAn apple of 180 g\n", outputStream.toString());
  }

  private List<Apple> createInventory() {
    List<Apple> inventory = new ArrayList<>();

    inventory.add(Apple.create("green", 120));
    inventory.add(Apple.create("red", 180));

    return inventory;
  }
}