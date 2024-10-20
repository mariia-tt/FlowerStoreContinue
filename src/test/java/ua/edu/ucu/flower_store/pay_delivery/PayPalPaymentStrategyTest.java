package ua.edu.ucu.flower_store.pay_delivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PayPalPaymentStrategyTest {

    private PayPalPaymentStrategy payPalStrategy;

    @Mock
    private BufferedReader mockReader;

    @BeforeEach
    void setUp() throws Exception {
        payPalStrategy = new PayPalPaymentStrategy();
        Field readerField = PayPalPaymentStrategy.class.getDeclaredField("READER");
        readerField.setAccessible(true);
        readerField.set(payPalStrategy, mockReader);

        Field dataBaseField = PayPalPaymentStrategy.class.getDeclaredField("DATA_BASE");
        dataBaseField.setAccessible(true);
        Map<String, String> dataBase = (Map<String, String>) dataBaseField.get(null);
        dataBase.clear();
        dataBase.put("password123", "test@example.com");
    }

    @Test
    void testCollectPaymentDetails_SuccessfulSignIn() throws IOException {
        when(mockReader.readLine())
                .thenReturn("test@example.com")
                .thenReturn("password123");

        payPalStrategy.collectPaymentDetails();

        verify(mockReader, times(2)).readLine();
    }

    @Test
    void testCollectPaymentDetails_FailedThenSuccessfulSignIn() throws IOException {
        when(mockReader.readLine())
                .thenReturn("wrong@example.com")
                .thenReturn("wrongpassword")
                .thenReturn("test@example.com")
                .thenReturn("password123");

        payPalStrategy.collectPaymentDetails();

        verify(mockReader, times(4)).readLine();
    }

    @Test
    void testPay_SignedIn() throws Exception {
        Field signedInField = PayPalPaymentStrategy.class.getDeclaredField("signedIn");
        signedInField.setAccessible(true);
        signedInField.set(payPalStrategy, true);

        assertTrue(payPalStrategy.pay(100));
    }

    @Test
    void testPay_NotSignedIn() {
        assertFalse(payPalStrategy.pay(100));
    }

    @Test
    void testVerify() throws Exception {
        Field emailField = PayPalPaymentStrategy.class.getDeclaredField("email");
        Field passwordField = PayPalPaymentStrategy.class.getDeclaredField("password");
        emailField.setAccessible(true);
        passwordField.setAccessible(true);

        emailField.set(payPalStrategy, "test@example.com");
        passwordField.set(payPalStrategy, "password123");
    
    }
}