package com.meylism.sparser;

import org.junit.Test;

import static org.junit.Assert.*;

public class SparserTest {
  @Test
  public void printTest() {
    Sparser sparser = new Sparser();
    sparser.filter("Heyyy", "jdks");
  }
}