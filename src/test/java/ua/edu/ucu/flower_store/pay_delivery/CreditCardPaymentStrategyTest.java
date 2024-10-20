package ua.edu.ucu.flower_store.pay_delivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreditCardPaymentStrategyTest {

    private CreditCardPaymentStrategy strategy;

    @Mock
    private CreditCard mockCard;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        strategy = new CreditCardPaymentStrategy();
    }

    @Test
    void testValidateCardNumber_Valid() {
        assertTrue(strategy.validateCardNumber("1234567890123456"));
    }

    @Test
    void testValidateCardNumber_Invalid() {
        assertFalse(strategy.validateCardNumber("12345"));
        assertFalse(strategy.validateCardNumber("abcdefghijklmnop"));
    }

    @Test
    void testValidateCVV_Valid() {
        assertTrue(strategy.validateCVV("123"));
        assertTrue(strategy.validateCVV("1234"));
    }

    @Test
    void testValidateCVV_Invalid() {
        assertFalse(strategy.validateCVV("12"));
        assertFalse(strategy.validateCVV("12345"));
        assertFalse(strategy.validateCVV("abc"));
    }

    @Test
    void testPay_CardPresent() {
        strategy.card = mockCard;
        when(mockCard.getAmount()).thenReturn(1000);

        boolean result = strategy.pay(500);

        assertTrue(result);
        verify(mockCard).setAmount(500);
    }

    @Test
    void testPay_CardNotPresent() {
        boolean result = strategy.pay(500);

        assertFalse(result);
    }
}
