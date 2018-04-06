package chapter6;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

public class PartitionPrimesExamples {

  public Map<Boolean, List<Integer>> partitionPrimes(int n) {
    return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(candidate -> isPrime(candidate)));
  }

  public Map<Boolean, List<Integer>> partitionPrimesWithCustomerCollector(int n) {
    return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
  }

  public boolean isPrime(int candidate) {
    int candidateRoot = (int) Math.sqrt((double) candidate);

    return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
  }
}
