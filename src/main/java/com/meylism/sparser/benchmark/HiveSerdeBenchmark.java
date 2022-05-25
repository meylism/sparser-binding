package com.meylism.sparser.benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meylism.sparser.Sparser;
import com.meylism.sparser.constants.BenchConstants;
import com.meylism.sparser.parser.HiveSerdeParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.hive.serde2.JsonSerDe;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.io.Text;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 3, time = 5)
@Fork(1)
@BenchmarkMode({ Mode.AverageTime})
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class HiveSerdeBenchmark {
  @Param({"twitter2.json"})
  public String resource;
  public String jsonText;
  public ArrayList<String> jsonAsLines;

  public HiveSerdeParser parser;


  Sparser sparser;
  String[] predicates;
  private JsonSerDe serde;

  @Benchmark
  public void withSparser(Blackhole bh) {
    long res = sparser.filter(jsonText, predicates);
    bh.consume(res);
  }

  @Benchmark
  public void withoutSparser(Blackhole bh) throws SerDeException {
    for (String line : jsonAsLines) {
      Object res = parser.deserialize(line);
      bh.consume(res);
    }
  }

  @Setup
  public void setup() throws IOException, SerDeException {
    // bench init
    jsonText = (String)Utils.loadJson(resource);
    jsonAsLines = (ArrayList<String>)Utils.loadJson(resource, false);
    predicates = new String[]{"elon", "musk"};

    // Serde init
    Properties props = new Properties();

    props.setProperty(serdeConstants.LIST_COLUMNS, BenchConstants.TWITTER_COLUMN_NAMES);
    props.setProperty(serdeConstants.LIST_COLUMN_TYPES, BenchConstants.TWITTER_COLUMN_TYPES);

    serde = new JsonSerDe();
    serde.initialize(new Configuration(), props, null);

    // Parser for Sparser
    parser = new HiveSerdeParser(serde);

    // Sparser
    sparser = new Sparser(parser);
  }

  public static void main(String[] args) throws RunnerException {
    System.out.println(Runtime.getRuntime().totalMemory());
    Options opt = new OptionsBuilder()
        .include(HiveSerdeBenchmark.class.getSimpleName())
        .build();
    new Runner(opt).run();
  }
}
