package com.company;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingStrategy implements MappingStrategy {

    @Override
    public Map<String, Integer> run(Stream<GithubEvent> inputStream) {
        return inputStream.collect(Collectors.groupingBy(x -> x.getType(), TreeMap::new, Collectors.summingInt(x -> 1)));
    }
}
