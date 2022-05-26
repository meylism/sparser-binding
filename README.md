# Sparser

Java binding for the Sparser library.

Sparser is a filtering library that lets you filter raw, unstructured or semi-structured data before parsing. To see 
how it does make sure you read the blog or paper below.

Blog by the authors: [here.](https://dawn.cs.stanford.edu/2018/08/07/sparser/) 

Paper of the research project: [here.](https://www.vldb.org/pvldb/vol11/p1576-palkar.pdf)

Code: [here.](https://github.com/stanford-futuredata/sparser)

We believe that data warehousing solutions such as Apache Hive will also benefit from this project and therefore 
wanted to experiment it. Since the project was originally written in C/C++, we bind it to Java through JNI.

## Running

The code to be bound(`sparser-jni`) is added as a dependency to this project. First clone the repo, then:

- Set up the submodule: 
  - ```bash
      git submodule init
      git submodule update
      ```
- Then go ahead and `cd` to `sparser-jni`. Then run `make all`. The script is supposed to do the following:
  - first build the `sparser-jni` project(using the Make file there). As a result the shared library(.dylib/.so) 
    will be put in the `sparser-jni/libs` directory. 
  - and then create a soft link to the library in a directory to which Java can reach(`java.library.path`).

If all went well, you should be able to the use `Sparser` class.

## Benchmark

To run the benchmarks locally:

- Build the project:
  - `mvn clean package`
- Run the jar:
  - `java -jar target/benchmarks.jar`