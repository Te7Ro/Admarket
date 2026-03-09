package kz.guccigang.admarket.service.payment;

import com.stripe.exception.StripeException;
import kz.guccigang.admarket.dto.payment.PaymentResponse;
import kz.guccigang.admarket.enums.PaymentStatus;

public interface PaymentService {
    String createCheckoutSession() throws StripeException;
    PaymentResponse updatePaymentStatus(Long paymentId, PaymentStatus paymentStatus);
    PaymentResponse confirmPayment(Long paymentId);
    PaymentResponse cancelPayment(Long paymentId);
}
