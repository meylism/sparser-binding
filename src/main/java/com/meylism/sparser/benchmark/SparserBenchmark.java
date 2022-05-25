package com.meylism.sparser.benchmark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meylism.sparser.Sparser;
import com.meylism.sparser.parser.JacksonParser;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 3, time = 3)
@Measurement(iterations = 3, time = 3)
@Fork(1)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class SparserBenchmark {
  @Param({"twitter.json", "twitter2.json"})
  public String resource;
  public String jsonText;

  ObjectMapper objectMapper;
  public ArrayList<String> forJackson;

  Sparser sparser;
  String[] predicates;

  @Benchmark
  public void parseWithoutSparser(Blackhole bh) throws JsonProcessingException {
    for (String line: forJackson) {
      JsonNode node = objectMapper.readTree(line);
      bh.consume(node);
    }
  }

  @Benchmark
  public void parseWithSparser(Blackhole bh) {
    Long res = sparser.filter(jsonText, predicates);
    bh.consume(res);
  }

  @Setup
  public void setup() throws IOException {
    objectMapper = new ObjectMapper();
    forJackson = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    Utils.loadJson(resource, sb, forJackson);
    jsonText = sb.toString();

    JacksonParser parser = new JacksonParser();
    sparser = new Sparser(parser);
    predicates = new String[]{"elon", "musk"};

  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(SparserBenchmark.class.getSimpleName())
        .build();
    new Runner(opt).run();
  }
}
