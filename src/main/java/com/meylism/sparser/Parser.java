package com.meylism.sparser;

public interface Parser {
  Object deserialize(String text) throws Exception;
}
