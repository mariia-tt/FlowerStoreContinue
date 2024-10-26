package ua.edu.ucu.flowerstore.paydelivery;

public interface Payment {
    void collectPaymentDetails();
    boolean pay(int paymentAmount); 
}
