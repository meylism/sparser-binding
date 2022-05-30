package com.meylism.sparser;

public class Sparser {
  private final Parser parser;

  public Sparser(Parser parser) {
    this.parser = parser;
  }

  static {
    System.loadLibrary("sparser");
  }

  public native void decompose(String[] predicates);

  public native void calibrate(String text);

  public native long filter(String text);

  public int parse(String text) throws Exception {
//    System.out.println(text);
    Object res = parser.deserialize(text);
    assert res != null;
    return 1;
  }
}
