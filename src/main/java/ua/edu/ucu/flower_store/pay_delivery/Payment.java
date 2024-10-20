package ua.edu.ucu.flower_store.pay_delivery;

public interface Payment {
    void collectPaymentDetails();
    boolean pay(int paymentAmount); 
}
