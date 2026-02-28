package kz.guccigang.admarket.controller.creator;

import jakarta.validation.Valid;
import kz.guccigang.admarket.dto.creator.platform.CreatorPlatformCreateRequest;
import kz.guccigang.admarket.dto.creator.platform.CreatorPlatformResponse;
import kz.guccigang.admarket.dto.creator.platform.CreatorPlatformUpdateRequest;
import kz.guccigang.admarket.service.creator.CreatorPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/creator/platform")
public class CreatorPlatformController {
    private final CreatorPlatformService creatorPlatformService;

    @GetMapping("{creatorId}")
    public ResponseEntity<List<CreatorPlatformResponse>> getCreatorPlatform(@PathVariable Long creatorId) {
        return ResponseEntity.ok(creatorPlatformService.getAllCreatorPlatforms(creatorId));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<CreatorPlatformResponse> createCreatorPlatform(@RequestBody @Valid CreatorPlatformCreateRequest request) {
        return ResponseEntity.ok(creatorPlatformService.createCreatorPlatform(request));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("{id}")
    public ResponseEntity<CreatorPlatformResponse> updateCreatorPlatform(@PathVariable Long id, @RequestBody @Valid CreatorPlatformUpdateRequest request) {
        return ResponseEntity.ok(creatorPlatformService.updateCreatorPlatform(id, request));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("{id}")
    public ResponseEntity<CreatorPlatformResponse> deleteCreatorPlatform(@PathVariable Long id) {
        creatorPlatformService.deleteCreatorPlatform(id);
        return ResponseEntity.ok().build();
    }
}
