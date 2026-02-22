package kz.guccigang.admarket.repository;

import kz.guccigang.admarket.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends BaseRepository<Category> {
    Optional<Category> findById(Long id);
    Optional<Category> findByName(String name);
    List<Category> findAllByOrderByNameAsc();
    boolean existsByName(String name);
}
