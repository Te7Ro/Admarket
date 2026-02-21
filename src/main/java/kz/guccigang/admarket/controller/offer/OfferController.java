package kz.guccigang.admarket.controller.offer;

import kz.guccigang.admarket.service.offer.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offer")
public class OfferController {
    private final OfferService offerService;
}
