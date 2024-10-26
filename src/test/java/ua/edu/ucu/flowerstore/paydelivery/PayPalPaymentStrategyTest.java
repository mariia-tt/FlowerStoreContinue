package ua.edu.ucu.flowerstore.paydelivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
class PayPalPaymentStrategyTest {

    private PayPalPaymentStrategy payPalStrategy;
    
    private static final int wantedNumberOfInvocationsOne = 2;
    private static final int wantedNumberOfInvocationsTwo = 4;
    private static final int paymentAmountOne = 100;
    private static final int paymentAmountTwo = 100;

    @Mock
    private BufferedReader mockReader;

    @BeforeEach
    void setUp() throws Exception {
        payPalStrategy = new PayPalPaymentStrategy();

        var readerField = PayPalPaymentStrategy.class.getDeclaredField("reader");
        readerField.setAccessible(true);
        readerField.set(payPalStrategy, mockReader);

        var dataBaseField = PayPalPaymentStrategy.class.getDeclaredField("DATA_BASE");
        dataBaseField.setAccessible(true);
        Map<String, String> dataBase = (Map<String, String>) dataBaseField.get(null);
        dataBase.clear();
        dataBase.put("password123", "test@example.com");
    }

    @Test
    void testCollectPaymentDetailsSuccessfulSignIn() throws IOException {
        Mockito.when(mockReader.readLine())
                .thenReturn("test@example.com")
                .thenReturn("password123");

        payPalStrategy.collectPaymentDetails();

        Mockito.verify(mockReader, Mockito.times(wantedNumberOfInvocationsOne))
        .readLine();
    }

    @Test
    void testCollectPaymentDetailsFailedThenSuccessfulSignIn() throws IOException {
        Mockito.when(mockReader.readLine())
                .thenReturn("wrong@example.com")
                .thenReturn("wrongpassword")
                .thenReturn("test@example.com")
                .thenReturn("password123");

        payPalStrategy.collectPaymentDetails();

        Mockito.verify(mockReader, Mockito.times(wantedNumberOfInvocationsTwo))
        .readLine();
    }

    @Test
    void testPaySignedIn() throws Exception {
        var signedInField = PayPalPaymentStrategy.class.getDeclaredField("signedIn");
        signedInField.setAccessible(true);
        signedInField.set(payPalStrategy, true);

        Assertions.assertTrue(payPalStrategy.pay(paymentAmountOne));
    }

    @Test
    void testPayNotSignedIn() {
        Assertions.assertFalse(payPalStrategy.pay(paymentAmountTwo));
    }

    @Test
    void testVerify() throws Exception {
        var emailField = PayPalPaymentStrategy.class.getDeclaredField("email");
        var passwordField = PayPalPaymentStrategy.class.getDeclaredField("password");
        emailField.setAccessible(true);
        passwordField.setAccessible(true);

        emailField.set(payPalStrategy, "test@example.com");
        passwordField.set(payPalStrategy, "password123");
    }
}
