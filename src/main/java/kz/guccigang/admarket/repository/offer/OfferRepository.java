package kz.guccigang.admarket.repository.offer;

import aj.org.objectweb.asm.commons.Remapper;
import kz.guccigang.admarket.entity.company.CompanyProfile;
import kz.guccigang.admarket.entity.offer.Offer;
import kz.guccigang.admarket.enums.OfferStatus;
import kz.guccigang.admarket.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface OfferRepository extends BaseRepository<Offer> {
    Page<Offer> findAllByCompany(CompanyProfile company, Pageable pageable);
    Page<Offer> findAll(Pageable pageable);
    Optional<Offer> findById(Long id);
    Optional<Offer> findByCompanyAndId(CompanyProfile company, Long id);

    Page<Offer> findAllByStatusIn(Collection<OfferStatus> statuses, Pageable pageable);
    Page<Offer> findAllByCompanyAndStatusIn(CompanyProfile company, Collection<OfferStatus> statuses, Pageable pageable);
}
