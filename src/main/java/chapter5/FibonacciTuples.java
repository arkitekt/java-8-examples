package chapter5;

import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FibonacciTuples {

  private Stream<int[]> createTuples(int limit) {
    return Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(limit);
  }

  public List<int[]> generateTuples(int limit) {
    return createTuples(limit).collect(toList());
  }

  public void generateAndPrintTuples(int limit) {
    createTuples(limit).forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
  }

  public void printFibonacciSeries(int limit) {
    createTuples(limit).forEach(t-> System.out.print(String.format("%s,", t[1])));
  }

  public void generateFibonacciSeriesUsingSupplier(int limit) {
    IntSupplier fibonacciSupplier = new IntSupplier() {
      private int current = 1;
      private int previous = 0;

      @Override
      public int getAsInt() {
        int oldPrevious = this.previous;
        int nextValue = this.previous + this.current;
        this.previous = this.current;
        this.current = nextValue;

        return oldPrevious;
      }
    };

    IntStream.generate(fibonacciSupplier).limit(limit).forEach(System.out::print);
  }
}
