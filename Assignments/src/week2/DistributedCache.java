package week2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DistributedCache {
    private final int L1_CAPACITY = 3;
    private LinkedHashMap<String, String> l1Cache;
    private HashMap<String, String> l2Cache = new HashMap<>();
    private HashMap<String, Integer> accessCount = new HashMap<>();

    public DistributedCache() {
        this.l1Cache = new LinkedHashMap<>(L1_CAPACITY, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > L1_CAPACITY;
            }
        };
    }

    public String getVideo(String videoId) {
        if (l1Cache.containsKey(videoId)) {
            System.out.println("L1 Cache HIT (0.5ms)");
            return l1Cache.get(videoId);
        }

        if (l2Cache.containsKey(videoId)) {
            System.out.println("L1 Cache MISS -> L2 Cache HIT (5ms)");
            String data = l2Cache.get(videoId);

            int count = accessCount.getOrDefault(videoId, 0) + 1;
            accessCount.put(videoId, count);

            if (count >= 2) {
                System.out.println("Promoting " + videoId + " to L1 (Frequent Access)");
                l1Cache.put(videoId, data);
            }
            return data;
        }

        System.out.println("L1 & L2 MISS. Fetching from Database (100ms)...");
        String dbData = "VideoData_for_" + videoId;
        l2Cache.put(videoId, dbData);
        return dbData;
    }

    public static void main(String[] args) {
        DistributedCache netflix = new DistributedCache();

        netflix.getVideo("video_123");

        System.out.println("\nRequesting video_123 again:");
        netflix.getVideo("video_123");

        System.out.println("\nRequesting video_123 third time:");
        netflix.getVideo("video_123");

        netflix.getVideo("vid_1");
        netflix.getVideo("vid_2");
        netflix.getVideo("vid_3");
        netflix.getVideo("vid_4");

        System.out.println("\nL1 Current State: " + netflix.l1Cache.keySet());
    }
}
