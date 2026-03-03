package kz.guccigang.admarket.controller.creator;

import jakarta.validation.Valid;
import kz.guccigang.admarket.dto.creator.geo.CreatorAudienceGeoCreateRequest;
import kz.guccigang.admarket.dto.creator.geo.CreatorAudienceGeoResponse;
import kz.guccigang.admarket.dto.creator.geo.CreatorAudienceGeoUpdateRequest;
import kz.guccigang.admarket.service.creator.CreatorAudienceGeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/creator/audience/geo")
public class CreatorAudienceGeoController {
    private final CreatorAudienceGeoService service;

    @GetMapping("{creatorId}")
    public ResponseEntity<List<CreatorAudienceGeoResponse>> getCreatorAudienceGeo(@PathVariable Long creatorId) {
        return ResponseEntity.ok(service.getAudienceGeoByCreatorId(creatorId));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<CreatorAudienceGeoResponse> createAudienceGeo(@RequestBody @Valid CreatorAudienceGeoCreateRequest request) {
        return ResponseEntity.ok(service.createAudienceGeo(request));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("{id}")
    public ResponseEntity<CreatorAudienceGeoResponse> updateAudienceGeo(@PathVariable Long id, @RequestBody @Valid CreatorAudienceGeoUpdateRequest request) {
        return ResponseEntity.ok(service.updateAudienceGeo(id, request));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("{id}")
    public ResponseEntity<CreatorAudienceGeoResponse> deleteAudienceGeo(@PathVariable Long id) {
        service.deleteAudienceGeo(id);
        return ResponseEntity.noContent().build();
    }
}
