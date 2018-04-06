package chapter6;

import org.junit.Before;
import org.junit.Test;

public class CollectorsPerformanceTest {

  private PartitionPrimesExamples uut;

  @Before
  public void setUp() throws Exception {
    uut = new PartitionPrimesExamples();
  }

  @Test
  public void testPerformance() throws Exception {
    long fastest = Long.MAX_VALUE;

    for (int i = 0; i < 10; i++) {
      long start = System.nanoTime();
      uut.partitionPrimes(1_000_000);
      long duration = (System.nanoTime() - start) / 1_000_000;

      if (duration < fastest) {
        fastest = duration;
      }
    }

    System.out.println("Fastest execution done in " + fastest + " msecs");
  }
}
