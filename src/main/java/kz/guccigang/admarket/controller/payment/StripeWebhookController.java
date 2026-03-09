package kz.guccigang.admarket.controller.payment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;

import io.swagger.v3.core.util.Json;
import kz.guccigang.admarket.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stripe")
@Slf4j
public class StripeWebhookController {

    @Value("${stripe.webhook-secret}")
    private String webhookSecret;

    private final PaymentService paymentService;

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader) {

        Event event;
        try {
            event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        if ("checkout.session.completed".equals(event.getType())) {
            log.info("Event JSON: {}", event.toJson());
            try {
                // Получаем raw JSON объекта
                JsonMapper jsonMapper = new JsonMapper();
                JsonNode jsonNode = jsonMapper.readTree(event.toJson());

                JsonNode data = jsonNode.get("data");
                JsonNode object = data.get("object");
                JsonNode metadata = object.get("metadata");

                String paymentId = metadata.get("paymentId").asText();
                log.info("Processing paymentId: {}", paymentId);
                paymentService.confirmPayment(Long.valueOf(paymentId));
            } catch (Exception e) {
                log.error("Failed to process checkout.session.completed", e);
            }
        }

        return ResponseEntity.ok().build();
    }
}
