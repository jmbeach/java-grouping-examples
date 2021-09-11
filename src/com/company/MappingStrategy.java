package com.company;

import java.util.Map;
import java.util.stream.Stream;

public interface MappingStrategy {
    Map<String, Integer> run(Stream<GithubEvent> inputStream);
}
