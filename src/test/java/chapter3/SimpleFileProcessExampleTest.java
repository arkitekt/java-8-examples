package chapter3;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author Olavi
 */
public class SimpleFileProcessExampleTest {

  private SimpleFileProcessExample example;

  @Before
  public void setUp() {
    example = new SimpleFileProcessExample();
  }

  @Test
  public void processFile() throws IOException {
    String result = example.processFile();

    assertThat(result, is("This is the first line."));
  }
}