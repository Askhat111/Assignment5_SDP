package User;

import java.time.LocalDateTime;

public class Transaction {
    private final String type;
    private final double amount;
    private final boolean success;
    private final LocalDateTime timestamp;

    public Transaction(String type, double amount, boolean success) {
        this.type = type;
        this.amount = amount;
        this.success = success;
        this.timestamp = LocalDateTime.now();
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public boolean isSuccess() { return success; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + type + ": " + amount + (success ? " OK" : " FAIL");
    }
}

