package com.meylism.sparser.benchmark;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;

public class Utils {
  public static Object loadJson(final String resourceName, boolean isString) throws IOException {
    final InputStream stream = Utils.class.getResourceAsStream("/" + resourceName);
    if (isString) {
      StringBuilder sb = new StringBuilder();
      return readFromInputStream(stream, sb);
    }
    ArrayList<String> lines = new ArrayList<>();
    readFromInputStream(stream, lines);
    return lines;
  }

  private static String readFromInputStream(InputStream inputStream, StringBuilder sb)
      throws IOException {
    try (BufferedReader br
        = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        sb.append(line).append("\n");
      }
      return sb.toString();
    }
  }

  private static void readFromInputStream(InputStream inputStream, ArrayList<String> lines)
      throws IOException {
    try (BufferedReader br
        = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        lines.add(line);
      }
    }
  }

  public static String loadJson(String resourceName) throws IOException {
    File file = new File(Utils.class.getClass().getResource("/" + resourceName).getFile());
    return FileUtils.readFileToString(file);
  }
}
