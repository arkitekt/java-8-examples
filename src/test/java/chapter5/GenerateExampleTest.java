package chapter5;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GenerateExampleTest {

  private GenerateExample uut;
  private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @Before
  public void setUp() throws Exception {
    System.setOut(new PrintStream(outputStream));
    uut = new GenerateExample();
  }

  @Test
  public void testPrintedGeneratedNumbers() throws Exception {
    uut.generateNumbers();

    String result = outputStream.toString();

    assertThat(result.contains("\n"), is(true));

    String[] items = result.split("\n");
    assertThat(items.length, is(5));
  }

  @Test
  public void testGettingGeneratedRandomNumbers() throws Exception {
    List<Double> result = uut.getGeneratedNumbers(10);

    assertThat(result.size(), is(10));
  }
}