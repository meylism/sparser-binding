package com.meylism.sparser.parser;

import com.meylism.sparser.Parser;
import org.apache.hadoop.hive.serde2.JsonSerDe;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.io.Text;

public class HiveSerdeParser implements Parser {
  private JsonSerDe serde;

  public HiveSerdeParser(JsonSerDe serde) {
    this.serde = serde;
  }

  @Override
  public Object deserialize(String text) throws SerDeException {
    return this.serde.deserialize(new Text(text));
  }
}
