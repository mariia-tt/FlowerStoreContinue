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
    
    int wantedNumberOfInvocations1 = 2;
    int wantedNumberOfInvocations2 = 4;
    int paymentAmount1 = 100;
    int paymentAmount2 = 100;

    @Mock
    private BufferedReader mockReader;

    @BeforeEach
    void setUp() throws Exception {
        payPalStrategy = new PayPalPaymentStrategy();

        Field readerField = PayPalPaymentStrategy.class.getDeclaredField("reader");
        readerField.setAccessible(true);
        readerField.set(payPalStrategy, mockReader);

        Field dataBaseField = PayPalPaymentStrategy.class.getDeclaredField("DATA_BASE");
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

        Mockito.verify(mockReader, Mockito.times(wantedNumberOfInvocations1)).readLine();
    }

    @Test
    void testCollectPaymentDetailsFailedThenSuccessfulSignIn() throws IOException {
        Mockito.when(mockReader.readLine())
                .thenReturn("wrong@example.com")
                .thenReturn("wrongpassword")
                .thenReturn("test@example.com")
                .thenReturn("password123");

        payPalStrategy.collectPaymentDetails();

        Mockito.verify(mockReader, Mockito.times(wantedNumberOfInvocations2)).readLine();
    }

    @Test
    void testPaySignedIn() throws Exception {
        Field signedInField = PayPalPaymentStrategy.class.getDeclaredField("signedIn");
        signedInField.setAccessible(true);
        signedInField.set(payPalStrategy, true);

        Assertions.assertTrue(payPalStrategy.pay(paymentAmount1));
    }

    @Test
    void testPayNotSignedIn() {
        Assertions.assertFalse(payPalStrategy.pay(paymentAmount2));
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


// package ua.edu.ucu.flowerstore.paydelivery;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import ua.edu.ucu.flowerstore.paydelivery.PayPalPaymentStrategy;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.lang.reflect.Field;
// import java.util.Map;

// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// //import static org.junit.jupiter.api.Assertions.*;
// //import static org.mockito.Mockito.*;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// @ExtendWith(MockitoExtension.class)
// class PayPalPaymentStrategyTest {

//     private PayPalPaymentStrategy payPalStrategy;
//     int WANTEDNUMBEROFINVOCATIONS1 = 2;
//     int WANTEDNUMBEROFINVOCATIONS2 = 4;
//     int PAYMENTAMOUNT1 = 100;
//     int PAYMENTAMOUNT2 = 100;

//     @Mock
//     private BufferedReader mockReader;

//     @BeforeEach
//     void setUp() throws Exception {
//         payPalStrategy = new PayPalPaymentStrategy();
//         Field readerField = PayPalPaymentStrategy.class
//         .getDeclaredField("reader");
//         readerField.setAccessible(true);
//         readerField.set(payPalStrategy, mockReader);

//         Field dataBaseField = PayPalPaymentStrategy.class
//         .getDeclaredField("DATA_BASE");
//         dataBaseField.setAccessible(true);
//         Map<String, String> dataBase
//          = (Map<String, String>) dataBaseField.get(null);
//         dataBase.clear();
//         dataBase.put("password123", "test@example.com");
//     }

//     @Test
//     void testCollectPaymentDetails_SuccessfulSignIn() throws IOException {
//         when(mockReader.readLine())
//                 .thenReturn("test@example.com")
//                 .thenReturn("password123");

//         payPalStrategy.collectPaymentDetails();

//         verify(mockReader, times(WANTEDNUMBEROFINVOCATIONS1)).readLine();
//     }

//     @Test
//     void testCollectPaymentDetails_FailedThenSuccessfulSignIn()
//      throws IOException {
//         when(mockReader.readLine())
//                 .thenReturn("wrong@example.com")
//                 .thenReturn("wrongpassword")
//                 .thenReturn("test@example.com")
//                 .thenReturn("password123");

//         payPalStrategy.collectPaymentDetails();

//         verify(mockReader, times(WANTEDNUMBEROFINVOCATIONS2)).readLine();
//     }

//     @Test
//     void testPay_SignedIn() throws Exception {
//         Field signedInField = PayPalPaymentStrategy.class
//         .getDeclaredField("signedIn");
//         signedInField.setAccessible(true);
//         signedInField.set(payPalStrategy, true);

//         assertTrue(payPalStrategy.pay(PAYMENTAMOUNT1));
//     }

//     @Test
//     void testPay_NotSignedIn() {
//         assertFalse(payPalStrategy.pay(PAYMENTAMOUNT2));
//     }

//     @Test
//     void testVerify() throws Exception {
//         Field emailField = PayPalPaymentStrategy.class
//         .getDeclaredField("email");
//         Field passwordField = PayPalPaymentStrategy.class
//         .getDeclaredField("password");
//         emailField.setAccessible(true);
//         passwordField.setAccessible(true);

//         emailField.set(payPalStrategy, "test@example.com");
//         passwordField.set(payPalStrategy, "password123");
    
//     }
// }