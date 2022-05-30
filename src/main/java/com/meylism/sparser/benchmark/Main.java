//package com.meylism.sparser.benchmark;
//
//import com.meylism.sparser.Sparser;
//import com.meylism.sparser.constants.BenchConstants;
//import com.meylism.sparser.parser.HiveSerdeParser;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hive.serde.serdeConstants;
//import org.apache.hadoop.hive.serde2.JsonSerDe;
//import org.apache.hadoop.hive.serde2.SerDeException;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Properties;
//
//public class Main {
//  public String resource = "twitter.json";
//  public String jsonText;
//  public ArrayList<String> jsonAsLines;
//
//  public HiveSerdeParser parser;
//
//
//  Sparser sparser;
//  String[] predicates;
//  private JsonSerDe serde;
//
//  public void setup() throws IOException, SerDeException {
//    System.out.println(Runtime.getRuntime().maxMemory());
//    // bench init
//    StringBuilder sb = new StringBuilder();
//    jsonAsLines = new ArrayList<>();
//    Utils.loadJson(resource, sb, jsonAsLines);
//    jsonText = sb.toString();
//    predicates = new String[]{"elon", "musk"};
//
//    // Serde init
//    Properties props = new Properties();
//
//    props.setProperty(serdeConstants.LIST_COLUMNS, BenchConstants.TWITTER_COLUMN_NAMES);
//    props.setProperty(serdeConstants.LIST_COLUMN_TYPES, BenchConstants.TWITTER_COLUMN_TYPES);
//
//    serde = new JsonSerDe();
//    serde.initialize(new Configuration(), props, null);
//
//    // Parser for Sparser
//    parser = new HiveSerdeParser(serde);
//
//    // Sparser
//    sparser = new Sparser(parser);
//    sparser.decompose(predicates);
//    sparser.calibrate(jsonText);
//  }
//
//  public static filter(Sparser sparser) throws IOException, SerDeException {
//    for (int i=0; i<=1; i++) {
//      long res = m.sparser.filter(m.jsonText);
//      System.out.println("\n\n\n\nRESULT: " + res + "\n\n\n\n");
//    }
//  }
//
//
//  public static void main(String[] args) throws IOException, SerDeException {
//    Main m = new Main();
//    m.setup();
//
//
//  }
//}
