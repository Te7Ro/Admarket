package kz.guccigang.admarket.controller.creator;

import jakarta.validation.Valid;
import kz.guccigang.admarket.dto.creator.CreatorResponse;
import kz.guccigang.admarket.dto.creator.CreatorUpdateRequest;
import kz.guccigang.admarket.service.UserService;
import kz.guccigang.admarket.service.creator.CreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/creator")
@Validated
public class CreatorController {
    private final CreatorService creatorService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<CreatorResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(creatorService.getById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CreatorResponse> getByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(creatorService.getByUserId(userId));
    }

    @GetMapping
    public Page<CreatorResponse> getAll(@PageableDefault Pageable pageable){
        return creatorService.getAll(pageable);
    }

    @PreAuthorize("hasAuthority('ADMIN') or #userId == principal.id")
    @PutMapping("/{userId}")
    public ResponseEntity<CreatorResponse> updateCreatorProfile(@PathVariable Long userId, @RequestBody @Valid CreatorUpdateRequest request) {
        return ResponseEntity.ok(creatorService.updateCreatorProfile(userId, request));
    }
}
