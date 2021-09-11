package com.company;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToMapStrategy implements MappingStrategy {
    public Map<String, Integer> run(Stream<GithubEvent> inputStream) {
        // Group the events by type and sum the number of events
        return inputStream
            .collect(Collectors.toMap(x -> x.getType(), x -> 1, (x, y) -> x + y));
    }
}
