package kz.guccigang.admarket.repository;

import kz.guccigang.admarket.entity.company.Industry;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndustryRepository extends BaseRepository<Industry>{
    Optional<Industry> findById(Long id);
}
