package com.company;

import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try {
            // Change me to see different strategies
            Strategy strategy = Strategy.ToMap;
            Dictionary<Strategy, MappingStrategy> strategyMap = new Hashtable<>();
            strategyMap.put(Strategy.ToMap, new ToMapStrategy());
            strategyMap.put(Strategy.Grouping, new GroupingStrategy());

            // Parse the file into class objects
            // Get this json file from https://data.gharchive.org/2021-09-01-11.json.gz
            Stream<GithubEvent> jsonObjects = Files.lines(Paths.get(System.getProperty("user.dir") + "/src/2021-09-01-11.json"))
                    .map(jsonLine -> new Gson().fromJson(jsonLine, GithubEvent.class))
                    // See how powerful .parallel is by commenting it out.
                    .parallel();
            Map<String, Integer> eventsByType = strategyMap.get(strategy).run(jsonObjects);
            for (var x: eventsByType.entrySet()) {
                System.out.println("There are " + x.getValue() + " " + x.getKey() + " events");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
