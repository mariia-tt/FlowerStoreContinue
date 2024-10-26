package ua.edu.ucu.flowerstore.paydelivery;

public class CreditCard {
    private static final int INITIAL_AMOUNT = 100000;
    private int amount;
    private String number;
    private String date;
    private String cvv; 

    CreditCard(String number, String date, String cvv) {
        this.amount = INITIAL_AMOUNT;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    public void setAmount(int newAmount) {
        this.amount = newAmount;
    }

    public int getAmount() {
        return amount;
    }
}
