package com.meylism.sparser.benchmark;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;

public class Utils {
  public static void loadJson(final String resourceName, StringBuilder sb, ArrayList<String> lines) throws IOException {
    final InputStream stream = Utils.class.getResourceAsStream("/" + resourceName);
    readFromInputStream(stream, sb, lines);
  }

  private static void readFromInputStream(InputStream inputStream, StringBuilder sb, ArrayList<String> lines)
      throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (sb != null)
          sb.append(line).append("\n");
        if (lines != null)
          lines.add(line);
      }
    }
  }
}
