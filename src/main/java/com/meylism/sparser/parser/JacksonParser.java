package com.meylism.sparser.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meylism.sparser.Parser;

public class JacksonParser implements Parser {
  private ObjectMapper mapper;

  public JacksonParser() {
    mapper = new ObjectMapper();
  }

  @Override
  public Object deserialize(String text) throws Exception {
    return mapper.readTree(text);
  }
}
