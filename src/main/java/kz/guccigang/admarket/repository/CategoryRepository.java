package kz.guccigang.admarket.repository;

import kz.guccigang.admarket.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends BaseRepository<Category> {
    Optional<Category> findById(Long id);
}
