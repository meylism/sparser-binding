package com.meylism.sparser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Sparser {
  private ObjectMapper mapper;

  public Sparser() {
    mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  static {
    System.loadLibrary("sparser");
  }

  public native long filter(String text, String[] predicates);

  public int parse(String text) throws Exception {
    try {
      JsonNode node = mapper.readTree(text);
      return node != null ? 1 : 0;
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e);
    }
  }
}
