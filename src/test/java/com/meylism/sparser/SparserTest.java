package com.meylism.sparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meylism.sparser.benchmark.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SparserTest {
  @Test
  public void withSparser() throws IOException {
    Sparser sparser = new Sparser();
    String jsonText = Utils.loadJson("twitter.json", new ArrayList<>());
//    String text = "{\"name\": \"Meylis\"}\n{\"name\": \"Mekan\"}";
    String predicates[] = new String[] {"elon", "musk", "twitter"};
    sparser.filter(jsonText, predicates);
  }

  @Test
  public void withoutSparser() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<String> data = new ArrayList<>();
    String jsonText = Utils.loadJson("twitter.json", data);
    for (String line: data) {
      JsonNode node = objectMapper.readTree(line);
      Assert.assertNotNull(node);
    }
    System.out.println(data.size());
  }
}