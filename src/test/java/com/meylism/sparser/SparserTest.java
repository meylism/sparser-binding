package com.meylism.sparser;

import org.junit.Test;

import static org.junit.Assert.*;

public class SparserTest {
  @Test
  public void printTest() {
    Sparser sparser = new Sparser();
    String line = "dsfsdksdksldsds";
    String predicates[] = new String[] {"Elon", "Musk"};
    sparser.filter(line, line.length(), predicates, predicates.length);
  }
}