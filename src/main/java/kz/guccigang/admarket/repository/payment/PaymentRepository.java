package kz.guccigang.admarket.repository.payment;

import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.payment.Payment;
import kz.guccigang.admarket.enums.PaymentStatus;
import kz.guccigang.admarket.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PaymentRepository extends BaseRepository<Payment> {
    Optional<Payment> findByIdAndPaymentStatusIn(Long id, Collection<PaymentStatus> paymentStatuses);
}
