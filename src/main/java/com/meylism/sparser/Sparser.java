package com.meylism.sparser;

public class Sparser {
  int i = 0;
  private final Parser parser;

  public Sparser(Parser parser) {
    this.parser = parser;
  }

  static {
    System.loadLibrary("sparser");
  }

  public native long filter(String text, String[] predicates);

  public int parse(String text) throws Exception {
//    i++;

//    System.out.println(i);
    Object res = parser.deserialize(text);
    assert res != null;
    return 1;
  }
}
