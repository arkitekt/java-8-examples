package chapter3;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Olavi
 */
public class FileProcessWithLambdaExampleTest {

  private FileProcessWithLambdaExample example;

  @Before
  public void setUp() throws Exception {
    example = new FileProcessWithLambdaExample();
  }

  @Test
  public void testRetrievingFirstLine() throws Exception {
    String result = example.processFile((BufferedReader br) -> br.readLine());

    assertThat(result, is("This is the first line."));
  }

  @Test
  public void testRetrievingFirstTwoLines() throws Exception {
    String result = example.processFile((BufferedReader br) -> String.format("%s %s", br.readLine(), br.readLine()));

    assertThat(result, is("This is the first line. This sentence is on second line."));
  }
}