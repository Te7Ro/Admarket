package kz.guccigang.admarket.util.mapper;

import kz.guccigang.admarket.dto.payment.PaymentResponse;
import kz.guccigang.admarket.entity.payment.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentMapper {

    @Mapping(target = "user", source = "user.email")
    PaymentResponse toDto(Payment payment);
}
