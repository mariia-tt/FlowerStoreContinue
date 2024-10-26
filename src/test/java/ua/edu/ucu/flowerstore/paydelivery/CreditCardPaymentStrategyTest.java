package ua.edu.ucu.flowerstore.paydelivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

class CreditCardPaymentStrategyTest {

    private static final int initialAmount = 1000;
    private static final int defaultPaymentAmount = 500;

    private CreditCardPaymentStrategy strategy;

    @Mock
    private CreditCard mockCard;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        strategy = new CreditCardPaymentStrategy();
        strategy.setCard(mockCard);
    }

    @Test
    void testValidateCardNumberValid() {
        Assertions.assertTrue(invokePrivateMethod("validateCardNumber",
         "1234567890123456"));
    }

    @Test
    void testValidateCardNumberInvalid() {
        Assertions.assertFalse(invokePrivateMethod("validateCardNumber",
         "12345"));
        Assertions.assertFalse(invokePrivateMethod("validateCardNumber",
         "abcdefghijklmnop"));
    }

    @Test
    void testValidateCVVValid() {
        Assertions.assertTrue(invokePrivateMethod("validateCVV",
         "123"));
        Assertions.assertTrue(invokePrivateMethod("validateCVV",
         "1234"));
    }

    @Test
    void testValidateCVVInvalid() {
        Assertions.assertFalse(invokePrivateMethod("validateCVV",
         "12"));
        Assertions.assertFalse(invokePrivateMethod("validateCVV",
         "12345"));
        Assertions.assertFalse(invokePrivateMethod("validateCVV",
         "abc"));
    }

    @Test
    void testPayCardPresent() {
        Mockito.when(mockCard.getAmount()).thenReturn(initialAmount);
        
        boolean result = strategy.pay(defaultPaymentAmount);

        Assertions.assertTrue(result);
        Mockito.verify(mockCard).setAmount(initialAmount - defaultPaymentAmount);
    }

    @Test
    void testPayCardNotPresent() {
        strategy.setCard(null);

        boolean result = strategy.pay(defaultPaymentAmount);

        Assertions.assertFalse(result);
    }

    private boolean invokePrivateMethod(String methodName, String parameter) {
        try {
            var method = CreditCardPaymentStrategy.class.getDeclaredMethod(methodName,
             String.class);
            method.setAccessible(true);
            return (boolean) method.invoke(strategy, parameter);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}