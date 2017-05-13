package chapter3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Olavi
 */
class SimpleFileProcessExample {

  private static final String FILE_LOCATION = "src/test/resources/data.txt";

  String processFile() throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_LOCATION))) {
      return br.readLine();
    }
  }
}
