package kz.guccigang.admarket.repository.creator;

import kz.guccigang.admarket.entity.creator.CreatorPlatform;
import kz.guccigang.admarket.entity.creator.CreatorProfile;
import kz.guccigang.admarket.enums.Platform;
import kz.guccigang.admarket.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreatorPlatformRepository extends BaseRepository<CreatorPlatform> {
    List<CreatorPlatform> findAllByCreator(CreatorProfile creator);

    Optional<CreatorPlatform> findByCreatorAndPlatform(CreatorProfile creator, Platform platform);
}
