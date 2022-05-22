package com.meylism.sparser.benchmark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Utils {
  public static String loadJson(final String resourceName, ArrayList<String> forJackson) throws IOException {
    final InputStream stream = Utils.class.getResourceAsStream("/" + resourceName);
    final String jsonText = readFromInputStream(stream, forJackson);
    return jsonText;
  }

  private static String readFromInputStream(InputStream inputStream, ArrayList<String> forJackson)
      throws IOException {
    StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br
        = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        forJackson.add(line);
        resultStringBuilder.append(line).append("\n");
      }
    }
    return resultStringBuilder.toString();
  }
}
