package com.meylism.sparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meylism.sparser.benchmark.Utils;
import com.meylism.sparser.constants.BenchConstants;
import com.meylism.sparser.parser.HiveSerdeParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.hive.serde2.JsonSerDe;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.*;

public class SparserTest {
  @Test
  public void testDeserialize() throws IOException, SerDeException {
    StringBuilder sb = new StringBuilder();
    Utils.loadJson("twitter.json", sb, null);
    String jsonText = sb.toString();

    Properties props = new Properties();

    props.setProperty(serdeConstants.LIST_COLUMNS, BenchConstants.TWITTER_COLUMN_NAMES);
    props.setProperty(serdeConstants.LIST_COLUMN_TYPES, BenchConstants.TWITTER_COLUMN_TYPES);

    JsonSerDe jsonSerDe = new JsonSerDe();
    jsonSerDe.initialize(new Configuration(), props, null);

    final Object result = jsonSerDe.deserialize(new Text(jsonText));
    Assert.assertNotNull(result);
  }
}