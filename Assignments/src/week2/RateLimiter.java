package week2;

import java.util.HashMap;

class TokenBucket {
    int tokens;
    long lastRefill;
    final int maxTokens = 10;
    final long refillInterval = 5000;

    TokenBucket() {
        this.tokens = maxTokens;
        this.lastRefill = System.currentTimeMillis();
    }
}

public class RateLimiter {
    private HashMap<String, TokenBucket> clients = new HashMap<>();

    public synchronized boolean checkRateLimit(String clientId) {
        TokenBucket bucket = clients.computeIfAbsent(clientId, k -> new TokenBucket());
        long now = System.currentTimeMillis();

        if (now - bucket.lastRefill > bucket.refillInterval) {
            bucket.tokens = bucket.maxTokens;
            bucket.lastRefill = now;
            System.out.println("Bucket refilled for client: " + clientId);
        }

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter();
        String user = "Client_A";

        for (int i = 1; i <= 12; i++) {
            boolean allowed = limiter.checkRateLimit(user);
            System.out.println("Request " + i + ": " + (allowed ? "ALLOWED" : "DENIED (Rate limit exceeded)"));
            if (i == 10) Thread.sleep(100);
        }

        System.out.println("\nWaiting for refill...");
        Thread.sleep(5100);

        System.out.println("Request 13: " + (limiter.checkRateLimit(user) ? "ALLOWED" : "DENIED"));
    }
}
