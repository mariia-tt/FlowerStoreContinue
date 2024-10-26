package ua.edu.ucu.flowerstore.paydelivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

class CreditCardPaymentStrategyTest {

    int INITIAL_AMOUNT = 1000;
    int DEFAULT_PAYMENT_AMOUNT = 500;

    private CreditCardPaymentStrategy strategy;

    @Mock
    private CreditCard mockCard;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        strategy = new CreditCardPaymentStrategy();
    }

    @Test
    void testValidateCardNumberValid() {
        Assertions.assertTrue(strategy.validateCardNumber("1234567890123456"));
    }

    @Test
    void testValidateCardNumberInvalid() {
        Assertions.assertFalse(strategy.validateCardNumber("12345"));
        Assertions.assertFalse(strategy.validateCardNumber("abcdefghijklmnop"));
    }

    @Test
    void testValidateCVVValid() {
        Assertions.assertTrue(strategy.validateCVV("123"));
        Assertions.assertTrue(strategy.validateCVV("1234"));
    }

    @Test
    void testValidateCVVInvalid() {
        Assertions.assertFalse(strategy.validateCVV("12"));
        Assertions.assertFalse(strategy.validateCVV("12345"));
        Assertions.assertFalse(strategy.validateCVV("abc"));
    }

    @Test
    void testPayCardPresent() {
        strategy.card = mockCard;
        Mockito.when(mockCard.getAmount()).thenReturn(INITIAL_AMOUNT);

        boolean result = strategy.pay(DEFAULT_PAYMENT_AMOUNT);

        Assertions.assertTrue(result);
        Mockito.verify(mockCard).setAmount(DEFAULT_PAYMENT_AMOUNT);
    }

    @Test
    void testPayCardNotPresent() {
        boolean result = strategy.pay(DEFAULT_PAYMENT_AMOUNT);

        Assertions.assertFalse(result);
    }
}