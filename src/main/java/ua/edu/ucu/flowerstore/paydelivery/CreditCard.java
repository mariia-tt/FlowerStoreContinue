package ua.edu.ucu.flowerstore.paydelivery;

public class CreditCard {
    private int amount;
    private String number;
    private String date;
    private String cvv;
    private int initialAmount = 100000; 

    CreditCard(String number, String date, String cvv) {
        this.amount = initialAmount;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
