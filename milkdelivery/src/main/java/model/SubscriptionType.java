package model;


public enum SubscriptionType {
    QUARTERLY(90), MONTHLY(30), YEARLY(365);

    public int getNumberOfDays() {
        return numberOfDays;
    }

    private int numberOfDays;

    SubscriptionType(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}