package kz.guccigang.admarket.service.company.impl;

import jakarta.transaction.Transactional;
import kz.guccigang.admarket.dto.industry.IndustryCreateRequest;
import kz.guccigang.admarket.dto.industry.IndustryResponse;
import kz.guccigang.admarket.entity.company.Industry;
import kz.guccigang.admarket.exception.entity.EntityAlreadyExistsException;
import kz.guccigang.admarket.repository.company.IndustryRepository;
import kz.guccigang.admarket.service.company.IndustryService;
import kz.guccigang.admarket.util.mapper.IndustryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndustryServiceImpl implements IndustryService {
    private final IndustryRepository industryRepository;
    private final IndustryMapper mapper;

    public List<IndustryResponse> getAllIndustry(){
        return industryRepository.findAllByOrderByNameAsc()
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public IndustryResponse getIndustryById(Long id){
        return industryRepository.findById(id).map(mapper::toDto).orElse(null);
    }

    public IndustryResponse getIndustryByName(String name){
        return industryRepository.findByName(name).map(mapper::toDto).orElse(null);
    }

    @Transactional
    public IndustryResponse createIndustry(IndustryCreateRequest request){
        if(industryRepository.existsByName(request.getName())){
            throw new EntityAlreadyExistsException("Industry already exists");
        }
        Industry industry = mapper.toEntity(request);
        industryRepository.save(industry);
        return mapper.toDto(industry);
    }

    @Transactional
    public void deleteIndustry(Long id){
        industryRepository.deleteById(id);
    }
}
