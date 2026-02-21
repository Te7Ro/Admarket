package kz.guccigang.admarket.controller.creator;

import jakarta.validation.Valid;
import kz.guccigang.admarket.dto.creator.CreatorCreateRequest;
import kz.guccigang.admarket.dto.creator.CreatorResponse;
import kz.guccigang.admarket.dto.creator.CreatorUpdateRequest;
import kz.guccigang.admarket.service.creator.CreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/creator")
@Validated
public class CreatorController {
    private final CreatorService creatorService;

    @GetMapping("{id}")
    public ResponseEntity<CreatorResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(creatorService.getById(id));
    }

    @GetMapping
    public Page<CreatorResponse> getAll(@PageableDefault Pageable pageable){
        return creatorService.getAll(pageable);
    }

    @PostMapping
    public ResponseEntity<CreatorResponse> createCompanyProfile(@RequestBody @Valid CreatorCreateRequest request) {
        return ResponseEntity.ok(creatorService.createCreatorProfile(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<CreatorResponse> updateCompanyProfile(@PathVariable Long id, @RequestBody @Valid CreatorUpdateRequest request) {
        return ResponseEntity.ok(creatorService.updateCreatorProfile(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CreatorResponse> deleteCompanyProfile(@PathVariable Long id){
        creatorService.deleteCreatorProfile(id);
        return ResponseEntity.ok().build();
    }
}
