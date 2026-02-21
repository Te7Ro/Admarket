package kz.guccigang.admarket.repository.company;

import kz.guccigang.admarket.entity.Category;
import kz.guccigang.admarket.entity.company.CompanyPreferredCategories;
import kz.guccigang.admarket.entity.company.CompanyProfile;
import kz.guccigang.admarket.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyPreferredCategoriesRepository extends BaseRepository<CompanyPreferredCategories> {
    List<CompanyPreferredCategories> findAllByCompany(CompanyProfile company);
    Optional<CompanyPreferredCategories> findByCompanyAndCategory(CompanyProfile company, Category category);
}
