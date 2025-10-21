package User;

import java.util.*;

public class User {
    private String username;
    private String password;
    private double bonusPoints;
    private List<Transaction> transactionHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bonusPoints = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    public double getBonusPoints() {
        return bonusPoints;
    }

    public void addBonus(double points) {
        System.out.println("Adding bonus " + points + " to user " + this.username);
        this.bonusPoints += points;
        System.out.println("New bonus total: " + this.bonusPoints);
    }

    public void addTransaction(Transaction tx) {
        transactionHistory.add(tx);
    }

    public List<Transaction> getHistory() {
        return transactionHistory;
    }
}

