package kz.guccigang.admarket.repository.creator;

import kz.guccigang.admarket.entity.creator.CreatorAudienceAge;
import kz.guccigang.admarket.entity.creator.CreatorProfile;
import kz.guccigang.admarket.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreatorAudienceAgeRepository extends BaseRepository<CreatorAudienceAge> {
    List<CreatorAudienceAge> findAllByCreator(CreatorProfile creator);
    Optional<CreatorAudienceAge> findByIdAndCreator(Long id, CreatorProfile creator);
}
