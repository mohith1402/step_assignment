package week1;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SocialMedia {
    private HashMap<String, String> usernames = new HashMap<>();
    private HashMap<String, Integer> attempts = new HashMap<>();

    public boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !usernames.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        suggestions.add(username + "1");
        suggestions.add(username + "2");
        suggestions.add(username.replace("_", "."));
        return suggestions;
    }

    public String getMostAttempted() {
        String most = null;
        int max = -1;
        for (Map.Entry<String, Integer> entry : attempts.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                most = entry.getKey();
            }
        }
        return most;
    }

    public static void main(String[] args) {
        SocialMedia system = new SocialMedia();

        System.out.println("Checking 'john_doe': " + system.checkAvailability("john_doe"));
        System.out.println("Alternatives: " + system.suggestAlternatives("john_doe"));

        system.checkAvailability("admin");
        system.checkAvailability("admin");
        System.out.println("Most attempted: " + system.getMostAttempted());
    }
}
