package com.meylism.sparser.parser;

import com.meylism.sparser.Sparser;
import com.meylism.sparser.benchmark.Utils;
import com.meylism.sparser.constants.BenchConstants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.hive.serde2.JsonSerDe;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HiveSerdeParserTest {
  @Test
  public void testDeserializeSparser() throws IOException, SerDeException {
    System.out.println(Runtime.getRuntime().maxMemory());
    StringBuilder sb = new StringBuilder();
    Utils.loadJson("twitter2.json", sb, null);
    String jsonText = sb.toString();
    String[] predicates = new String[]{"meylis", "matiyev"};

    Properties props = new Properties();

    props.setProperty(serdeConstants.LIST_COLUMNS, BenchConstants.TWITTER_COLUMN_NAMES);
    props.setProperty(serdeConstants.LIST_COLUMN_TYPES, BenchConstants.TWITTER_COLUMN_TYPES);

    JsonSerDe jsonSerDe = new JsonSerDe();
    jsonSerDe.initialize(new Configuration(), props, null);

    HiveSerdeParser hiveSerdeParser = new HiveSerdeParser(jsonSerDe);
    Sparser sparser = new Sparser(hiveSerdeParser);

    long res = sparser.filter(jsonText, predicates);

    Assert.assertNotNull(res);
  }

  @Test
  public void testDeserializeWithoutSparser() throws IOException, SerDeException {
    ArrayList<String> lines = new ArrayList<>();
    Utils.loadJson("twitter2.json", null, lines);

    Properties props = new Properties();

    props.setProperty(serdeConstants.LIST_COLUMNS, BenchConstants.TWITTER_COLUMN_NAMES);
    props.setProperty(serdeConstants.LIST_COLUMN_TYPES, BenchConstants.TWITTER_COLUMN_TYPES);

    JsonSerDe jsonSerDe = new JsonSerDe();
    jsonSerDe.initialize(new Configuration(), props, null);

    for (String line : lines) {
      Object result = jsonSerDe.deserialize(new Text(line));
      Assert.assertNotNull(result);
    }
  }
}
