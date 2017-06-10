package chapter5;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GenerateExample {

  public void generateNumbers() {
    Stream.generate(Math::random).limit(5).forEach(System.out::println);
  }

  public List<Double> getGeneratedNumbers(int count ) {
    return Stream.generate(Math::random).limit(count).collect(toList());
  }
}
