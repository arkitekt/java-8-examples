package chapter3;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Olavi
 */
public class TestSorting {

  @Test
  public void testSortingOfStrings() throws Exception {
    List<String> source = Arrays.asList("t", "a", "f", "q");
    source.sort((one, other) -> one.compareToIgnoreCase(other));

    assertThat(source.toString(), is("[a, f, q, t]"));
  }
}
