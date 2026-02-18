package kz.guccigang.admarket.repository;

import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.company.CompanyProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CompanyRepository extends BaseRepository<CompanyProfile> {
    Optional<CompanyProfile> findById(Long id);
    Page<CompanyProfile> findAll(Pageable pageable);

    boolean existsByUser(User user);
}
