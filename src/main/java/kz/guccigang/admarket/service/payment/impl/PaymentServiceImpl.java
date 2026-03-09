package kz.guccigang.admarket.service.payment.impl;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.transaction.Transactional;
import kz.guccigang.admarket.dto.payment.PaymentResponse;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.payment.Payment;
import kz.guccigang.admarket.enums.PaymentStatus;
import kz.guccigang.admarket.enums.UserStatus;
import kz.guccigang.admarket.exception.entity.EntityNotFoundException;
import kz.guccigang.admarket.repository.UserRepository;
import kz.guccigang.admarket.repository.payment.PaymentRepository;
import kz.guccigang.admarket.service.AuthenticationService;
import kz.guccigang.admarket.service.payment.PaymentService;
import kz.guccigang.admarket.util.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final AuthenticationService authService;
    private final PaymentMapper paymentMapper;
    private final UserRepository userRepository;

    @Value("${stripe.success-url}")
    private String successUrl;

    @Value("${stripe.cancel-url}")
    private String cancelUrl;

    @Transactional
    public String createCheckoutSession() throws StripeException {
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setAmount(10L);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        paymentRepository.save(payment);

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)

                        .setSuccessUrl(successUrl + "?session_id={CHECKOUT_SESSION_ID}")
                        .setCancelUrl(cancelUrl)

                        .putMetadata("paymentId", payment.getId().toString())

                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency("usd")
                                                        .setUnitAmount(1000L)
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData
                                                                        .builder()
                                                                        .setName("Order #" + payment.getId())
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        Session session = Session.create(params);

        return session.getUrl();
    }

    public PaymentResponse updatePaymentStatus(Long paymentId, PaymentStatus paymentStatus) {
        final Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("Транзакция не найдена"));

        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);

        return paymentMapper.toDto(payment);
    }

    @Transactional
    public PaymentResponse confirmPayment(Long paymentId) {

        Payment payment = paymentRepository.findByIdAndPaymentStatusIn(paymentId, List.of(PaymentStatus.PENDING))
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        payment.setPaymentStatus(PaymentStatus.PAID);
        User user = payment.getUser();
        user.setStatus(UserStatus.SUBSCRIBED);

        paymentRepository.save(payment);
        userRepository.save(user);

        return paymentMapper.toDto(payment);
    }

    @Transactional
    public PaymentResponse cancelPayment(Long paymentId) {
        Payment payment = paymentRepository.findByIdAndPaymentStatusIn(paymentId, List.of(PaymentStatus.PENDING))
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        payment.setPaymentStatus(PaymentStatus.FAILED);

        paymentRepository.save(payment);

        return paymentMapper.toDto(payment);
    }

}
