package chapter5;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FibonacciTuplesTest {

  private FibonacciTuples uut;
  private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @Before
  public void setUp() throws Exception {
    System.setOut(new PrintStream(outputStream));
    uut = new FibonacciTuples();
  }

  @Test
  public void test() throws Exception {
    List<int[]> result = uut.generateTuples(10);

    assertThat(result.size(), is(10));

    assertThat(result.get(0)[0], is(0));
    assertThat(result.get(0)[1], is(1));
    assertThat(result.get(1)[0], is(1));
    assertThat(result.get(1)[1], is(1));
    assertThat(result.get(2)[0], is(1));
    assertThat(result.get(2)[1], is(2));
    assertThat(result.get(3)[0], is(2));
    assertThat(result.get(3)[1], is(3));
    assertThat(result.get(4)[0], is(3));
    assertThat(result.get(4)[1], is(5));
    assertThat(result.get(5)[0], is(5));
    assertThat(result.get(5)[1], is(8));
  }

  @Test
  public void testGeneratingAndPrintingTuples() throws Exception {
    uut.generateAndPrintTuples(10);

    assertThat(outputStream.toString(), is(("(0,1)\n(1,1)\n(1,2)\n(2,3)\n(3,5)\n(5,8)\n(8,13)\n(13,21)\n(21,34)\n(34,55)\n")));
  }

  @Test
  public void testGeneratingAndPrintingFibonacciSeries() throws Exception {
    uut.printFibonacciSeries(11);

    assertThat(outputStream.toString(), is("1,1,2,3,5,8,13,21,34,55,89,"));
  }

  @Test
  public void testPrintingFibonacciSeriesUsingSupplier() throws Exception {
    uut.generateFibonacciSeriesUsingSupplier(13);

    assertThat(outputStream.toString(), is("01123581321345589144"));
  }
}