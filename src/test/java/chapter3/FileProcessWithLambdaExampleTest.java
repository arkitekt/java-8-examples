package chapter3;

import org.junit.Before;
import org.junit.Test;

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
  public void processFile() throws Exception {
    String result = example.processFile();

    assertThat(result, is("This is the first line."));
  }

}