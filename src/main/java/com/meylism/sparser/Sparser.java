package com.meylism.sparser;

public class Sparser {
  static {
    System.loadLibrary("sparser");
  }

  public native long filter(String line, int lineLength, String[] predicates, int numberOfPredicates);

  public static native void init();
}
