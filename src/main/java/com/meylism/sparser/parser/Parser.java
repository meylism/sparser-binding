package com.meylism.sparser.parser;

public interface Parser {
  Object deserialize(String text) throws Exception;
}
