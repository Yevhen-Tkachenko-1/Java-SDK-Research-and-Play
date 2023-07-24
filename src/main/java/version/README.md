## Java 9 – 17 features

### Java 9
- Modules
- Try-with-resources syntax
- Interface private methods
- List.of(), Set.of(), Map.of()
- Optional.stream()

### Java 10
- Local variable VAR
- List.copyOf(), Set.copyOf(), Map.copyOf()
- Collectors.toUnmofiableList(), Collectors.toUnmodifiableSet(), Collectors.toUnmodifiableMap()
- Optional.orElseThrow()

### Java 11
- First LTS version
- OracleSDK vs OpenSDK
- String.repeat(), String.isBlank(), String.lines(), String.strip()
- Collection.toArray(IntFunction<T[]> generator)
- Running Java files:  $ java HelloWorld.java

### Java 12
- String.indent(), String.transform()
- File.mismatch()
- Collectors.teeing()
- Switch expression: lambda style
- Pattern matching: instanceof variable

### Java 13
- Switch expression (yield)
- Text blocks

### Java 14
- Text blocks (visual line breaks)
- Records
- NullPointerException stack trace printing

### Java 15
- Sealed classes
- InstanceOf binding variable

### Java 16
- Stream.toList()

### Java 17
- Switch expression: pattern matches for classes
- RandomGeneratorFactory class

### Java 19
- HashMap.newHashMap()
- Pattern matching for Records (instanceof and switch)
- Switch expression (when)

## Summary

### Non-code changes
- Modules, LTS, OracleSDK vs OpenSDK, Running Java files, Garbage collector

### Updated APIs
- Collections:
  -	List.of(), List.copyOf()
  -	Set.of(), Set.copyOf()
  -	Map.of(), Map.copyOf(), HashMap.newHashMap()
  -	Collection.toArray(IntFunction<T[]> generator)
- Streams:
  -	Collectors.toUnmodifiableList()
  -	Collectors.toUnmodifiableSet()
  -	Collectors.toUnmodifiableMap()
  -	Collectors.teeing()
  - Stream.toList()
- Optional:
  - Optional.orElseThrow()
  - Optional.stream()
- String:
  - String.repeat()
  - String.isBlank()
  - String.strip()
  - String.lines()
  - String.indent()
  - String.transform()

### Updated features
- Try-with-resources, Interface private methods, switch, instanceof

### Introduced features
- HTTPClient, var, Text blocks, Records, Sealed


