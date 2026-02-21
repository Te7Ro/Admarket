package kz.guccigang.admarket.repository.creator;

import kz.guccigang.admarket.entity.creator.CreatorAudienceAge;
import kz.guccigang.admarket.entity.creator.CreatorProfile;
import kz.guccigang.admarket.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreatorAudienceAgeRepository extends BaseRepository<CreatorAudienceAge> {
    List<CreatorAudienceAge> findByCreator(CreatorProfile creator);
}
