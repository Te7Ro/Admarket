package kz.guccigang.admarket.repository;

import kz.guccigang.admarket.entity.Country;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends BaseRepository<Country>{
    Optional<Country> findById(Long id);
    Optional<Country> findByCode(String code);
    List<Country> findAllByOrderByNameAsc();
    boolean existsByCodeOrName(String code, String name);
}
