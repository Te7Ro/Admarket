package kz.guccigang.admarket.controller.creator;

import jakarta.validation.Valid;
import kz.guccigang.admarket.dto.creator.age.CreatorAudienceAgeCreateRequest;
import kz.guccigang.admarket.dto.creator.age.CreatorAudienceAgeResponse;
import kz.guccigang.admarket.dto.creator.age.CreatorAudienceAgeUpdateRequest;
import kz.guccigang.admarket.service.creator.CreatorAudienceAgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/creator/audience/age")
public class CreatorAudienceAgeController {
    private final CreatorAudienceAgeService service;

    @GetMapping("{creatorId}")
    public ResponseEntity<List<CreatorAudienceAgeResponse>> getCreatorAudienceAgeByCreatorId(@PathVariable Long creatorId) {
        return ResponseEntity.ok(service.getAudienceAgeByCreatorId(creatorId));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<CreatorAudienceAgeResponse> createAudienceAge(@RequestBody @Valid CreatorAudienceAgeCreateRequest request) {
        return ResponseEntity.ok(service.createAudienceAge(request));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("{id}")
    public ResponseEntity<CreatorAudienceAgeResponse> updateAudienceAge(@PathVariable Long id, @RequestBody @Valid CreatorAudienceAgeUpdateRequest request) {
        return ResponseEntity.ok(service.updateAudienceAge(id, request));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("{id}")
    public ResponseEntity<CreatorAudienceAgeResponse> deleteAudienceAge(@PathVariable Long id) {
        service.deleteAudienceAge(id);
        return ResponseEntity.noContent().build();
    }
}
