package ua.edu.ucu.flowerstore.paydelivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreditCardPaymentStrategy implements Payment {
    private final BufferedReader READER = new BufferedReader(
        new InputStreamReader(System.in));
    CreditCard card;

    @Override
    public void collectPaymentDetails() {
        try {
            System.out.print("Enter the card number: ");
            String number = READER.readLine();
            System.out.print("Enter the card expiration date 'mm/yy': ");
            String date = READER.readLine();
            System.out.print("Enter the CVV code: ");
            String cvv = READER.readLine();

            if (validateCard(number, cvv)) {
                card = new CreditCard(number, date, cvv);
                System.out.println("Card is valid.");
            } else {
                System.out.println("Invalid card. Please try one more time.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean validateCard(String number, String cvv) {
        return validateCardNumber(number) && validateCVV(cvv);
    }

    boolean validateCardNumber(String number) {
        return number.matches("\\d{16}");
    }

    boolean validateCVV(String cvv) {
        return cvv.matches("\\d{3,4}");
    }
    
    @Override
    public boolean pay(int paymentAmount) {
        if (cardIsPresent()) {
            System.out.println("Paying " + paymentAmount
             + " using Credit Card.");
            card.setAmount(card.getAmount() - paymentAmount);
            return true;
        } else {
            return false;
        }
    }

    private boolean cardIsPresent() {
        return card != null;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }
}