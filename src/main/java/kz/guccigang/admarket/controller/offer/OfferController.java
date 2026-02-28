package kz.guccigang.admarket.controller.offer;

import jakarta.validation.Valid;
import kz.guccigang.admarket.dto.offer.OfferCreateRequest;
import kz.guccigang.admarket.dto.offer.OfferResponse;
import kz.guccigang.admarket.dto.offer.OfferUpdateRequest;
import kz.guccigang.admarket.enums.OfferStatus;
import kz.guccigang.admarket.service.offer.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offer")
public class OfferController {
    private final OfferService offerService;

    @GetMapping
    public Page<OfferResponse> getOffers(@PageableDefault Pageable pageable) {
        return offerService.getAllOffers(pageable);
    }

    @GetMapping("/company/{companyId}")
    public Page<OfferResponse> getOffersByCompany(@PathVariable Long companyId, @PageableDefault Pageable pageable) {
        return offerService.getOffersByCompany(companyId, pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<OfferResponse> getOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(offerService.getOfferById(id));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<OfferResponse> createOffer(@RequestBody @Valid OfferCreateRequest offerCreateRequest) {
        return ResponseEntity.ok(offerService.createOffer(offerCreateRequest));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("{id}")
    public ResponseEntity<OfferResponse> updateOffer(@PathVariable Long id, @RequestBody @Valid OfferUpdateRequest offerUpdateRequest) {
        return ResponseEntity.ok(offerService.updateOffer(id, offerUpdateRequest));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/status/{id}")
    public ResponseEntity<OfferResponse> updateStatus(@PathVariable Long id, @RequestParam OfferStatus offerStatus) {
        return ResponseEntity.ok(offerService.updateOfferStatus(id, offerStatus));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping
    public ResponseEntity<OfferResponse> deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }
}
