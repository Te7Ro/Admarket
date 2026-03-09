package kz.guccigang.admarket.dto.payment;

import kz.guccigang.admarket.enums.PaymentStatus;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class PaymentResponse {
    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private String user;
    private Long amount;
    private PaymentStatus paymentStatus;
}
