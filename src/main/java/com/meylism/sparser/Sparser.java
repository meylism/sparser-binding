package com.meylism.sparser;

public class Sparser {
  static {
    System.out.println(System.getProperty("java.library.path"));
    System.loadLibrary("libsparser");
    init();
  }

  public native long filter(String line, String predicate);

  public static native void init();
}
