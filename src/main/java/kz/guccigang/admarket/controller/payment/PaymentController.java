package kz.guccigang.admarket.controller.payment;

import com.stripe.exception.StripeException;
import kz.guccigang.admarket.dto.payment.PaymentResponse;
import kz.guccigang.admarket.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/checkout")
    public ResponseEntity<String> checkout() throws StripeException {
        String url = paymentService.createCheckoutSession();
        return ResponseEntity.ok(url);
    }

    @GetMapping("/test-success")
    public String success() {
        return "Payment success";
    }

    @GetMapping("/test-cancel")
    public String cancel() {
        return "Payment canceled";
    }
}
