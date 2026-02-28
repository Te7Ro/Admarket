package kz.guccigang.admarket.controller.offer;

import kz.guccigang.admarket.dto.offer.OfferTargetCountryResponse;
import kz.guccigang.admarket.entity.Country;
import kz.guccigang.admarket.service.offer.OfferTargetCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offer/target_country")
public class OfferTargetCountryController {
    private final OfferTargetCountryService offerTargetCountryService;

    @GetMapping("{offerId}")
    public ResponseEntity<List<OfferTargetCountryResponse>> getOfferTargetCountry(@PathVariable Long offerId) {
        return ResponseEntity.ok(offerTargetCountryService.getOfferTargetCountries(offerId));
    }

    @PostMapping("{offerId}")
    public ResponseEntity<List<OfferTargetCountryResponse>> addOfferTargetCountry(@PathVariable Long offerId, @RequestParam Country country) {
        return ResponseEntity.ok(offerTargetCountryService.addOfferTargetCountry(offerId, country));
    }

    @DeleteMapping("{offerId}")
    public ResponseEntity<List<OfferTargetCountryResponse>> deleteOfferTargetCountry(@PathVariable Long offerId, @RequestParam Country country) {
        return ResponseEntity.ok(offerTargetCountryService.removeOfferTargetCountry(offerId, country));
    }
}
