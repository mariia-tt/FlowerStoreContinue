package ua.edu.ucu.flowerstore.paydelivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
class PayPalPaymentStrategyTest {

    private static final int WANTED_NUMBER_OF_INVOCATIONS_ONE = 2;
    private static final int WANTED_NUMBER_OF_INVOCATIONS_TWO = 4;
    private static final int PAYMENT_AMOUNT_ONE = 100;
    private static final int PAYMENT_AMOUNT_TWO = 100;

    private PayPalPaymentStrategy payPalStrategy;

    @Mock
    private BufferedReader mockReader;

    @BeforeEach
    void setUp() throws Exception {
        payPalStrategy = new PayPalPaymentStrategy();

        var readerField = PayPalPaymentStrategy.class
        .getDeclaredField("reader");
        readerField.setAccessible(true);
        readerField.set(payPalStrategy, mockReader);

        var dataBaseField = PayPalPaymentStrategy.class
        .getDeclaredField("DATA_BASE");
        dataBaseField.setAccessible(true);
        Map<String, String> dataBase
         = (Map<String, String>) dataBaseField.get(null);
        dataBase.clear();
        dataBase.put("password123", "test@example.com");
    }

    @Test
    void testCollectPaymentDetailsSuccessfulSignIn() throws IOException {
        Mockito.when(mockReader.readLine())
                .thenReturn("test@example.com")
                .thenReturn("password123");

        payPalStrategy.collectPaymentDetails();

        Mockito.verify(mockReader, Mockito.times(
            WANTED_NUMBER_OF_INVOCATIONS_ONE))
                .readLine();
    }

    @Test
    void testCollectPaymentDetailsFailedThenSuccessfulSignIn()
     throws IOException {
        Mockito.when(mockReader.readLine())
                .thenReturn("wrong@example.com")
                .thenReturn("wrongpassword")
                .thenReturn("test@example.com")
                .thenReturn("password123");

        payPalStrategy.collectPaymentDetails();

        Mockito.verify(mockReader, Mockito.times(
            WANTED_NUMBER_OF_INVOCATIONS_TWO))
                .readLine();
    }

    @Test
    void testPaySignedIn() throws Exception {
        var signedInField = PayPalPaymentStrategy.class
        .getDeclaredField("signedIn");
        signedInField.setAccessible(true);
        signedInField.set(payPalStrategy, true);

        Assertions.assertTrue(payPalStrategy.pay(PAYMENT_AMOUNT_ONE));
    }

    @Test
    void testPayNotSignedIn() {
        Assertions.assertFalse(payPalStrategy.pay(PAYMENT_AMOUNT_TWO));
    }

    @Test
    void testVerify() throws Exception {
        var emailField = PayPalPaymentStrategy.class
        .getDeclaredField("email");
        var passwordField = PayPalPaymentStrategy.class
        .getDeclaredField("password");
        emailField.setAccessible(true);
        passwordField.setAccessible(true);

        emailField.set(payPalStrategy, "test@example.com");
        passwordField.set(payPalStrategy, "password123");
    }
}
