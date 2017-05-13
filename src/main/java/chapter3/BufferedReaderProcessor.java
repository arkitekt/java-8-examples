package chapter3;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Olavi
 */
@FunctionalInterface
public interface BufferedReaderProcessor {

  String process(BufferedReader br) throws IOException;

}
