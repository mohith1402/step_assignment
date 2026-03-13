package week2;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AutocompleteSystem {
    private HashMap<String, Integer> queryFreq = new HashMap<>();

    public void updateFrequency(String query) {
        queryFreq.put(query, queryFreq.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {
        return queryFreq.keySet().stream()
                .filter(q -> q.toLowerCase().startsWith(prefix.toLowerCase()))
                .sorted((a, b) -> queryFreq.get(b) - queryFreq.get(a))
                .limit(5)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        AutocompleteSystem searchBar = new AutocompleteSystem();

        searchBar.updateFrequency("how to learn java");
        searchBar.updateFrequency("how to learn java");
        searchBar.updateFrequency("how to learn javascript");
        searchBar.updateFrequency("hello world");
        searchBar.updateFrequency("how to bake a cake");

        System.out.println("Suggestions for 'how':");
        List<String> results = searchBar.search("how");
        results.forEach(res -> System.out.println("- " + res + " (Score: " + searchBar.queryFreq.get(res) + ")"));
    }
}