package kz.guccigang.admarket.repository.company;

import kz.guccigang.admarket.entity.company.Industry;
import kz.guccigang.admarket.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IndustryRepository extends BaseRepository<Industry> {
    Optional<Industry> findById(Long id);
    Optional<Industry> findByName(String name);
    List<Industry> findAllByOrderByNameAsc();
    boolean existsByName(String name);
}
