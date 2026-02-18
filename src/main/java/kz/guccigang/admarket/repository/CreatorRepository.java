package kz.guccigang.admarket.repository;

import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.creator.CreatorProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreatorRepository extends BaseRepository<CreatorProfile>{
    Optional<CreatorProfile> findById(Long id);
    Page<CreatorProfile> findAll(Pageable pageable);

    boolean existsByUser(User user);
}
