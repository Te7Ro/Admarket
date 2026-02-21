package kz.guccigang.admarket.repository.creator;

import kz.guccigang.admarket.entity.Country;
import kz.guccigang.admarket.entity.creator.CreatorAudienceGeo;
import kz.guccigang.admarket.entity.creator.CreatorProfile;
import kz.guccigang.admarket.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreatorAudienceGeoRepository extends BaseRepository<CreatorAudienceGeo> {
    List<CreatorAudienceGeo> findAllByCreator(CreatorProfile creator);
    Optional<CreatorAudienceGeo> findByCreatorAndCountry(CreatorProfile creator, Country country);
}
