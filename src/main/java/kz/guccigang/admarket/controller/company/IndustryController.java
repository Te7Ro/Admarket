package kz.guccigang.admarket.controller.company;

import kz.guccigang.admarket.dto.industry.IndustryCreateRequest;
import kz.guccigang.admarket.dto.industry.IndustryResponse;
import kz.guccigang.admarket.service.company.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/industry")
public class IndustryController {
    private final IndustryService industryService;

    @GetMapping
    public ResponseEntity<List<IndustryResponse>> getAll() {
        return ResponseEntity.ok(industryService.getAllIndustry());
    }

    @GetMapping("{id}")
    public ResponseEntity<IndustryResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(industryService.getIndustryById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<IndustryResponse> create(@RequestBody IndustryCreateRequest request) {
        return ResponseEntity.ok(industryService.createIndustry(request));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        industryService.deleteIndustry(id);
        return ResponseEntity.noContent().build();
    }
}
