package mk.ukim.finki.wp.june2025g1.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.june2025g1.model.Industry;
import mk.ukim.finki.wp.june2025g1.model.Startup;
import mk.ukim.finki.wp.june2025g1.repository.FounderRepository;
import mk.ukim.finki.wp.june2025g1.repository.StartupRepository;
import mk.ukim.finki.wp.june2025g1.service.StartupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.june2025g1.service.FieldFilterSpecification.*;

@Service
@AllArgsConstructor
public class StartupServiceImpl implements StartupService {
    private final StartupRepository startupRepository;
    private final FounderRepository founderRepository;

    @Override
    public List<Startup> listAll() {
        return startupRepository.findAll();
    }

    @Override
    public Startup findById(Long id) throws Exception {
        if(id == null){
            throw new Exception("Invalid ID for Startup");
        }
        return startupRepository.findById(id).orElseThrow();
    }

    @Override
    public Startup create(String name, Double valuation, Integer yearFounded, Industry industry, Long founderId) throws Exception {
        if(name == null || valuation == null || yearFounded == null || industry == null || founderId == null){
            throw new Exception("Invalid parameters.");
        }
        return startupRepository
                .save(
                new Startup(name, valuation, yearFounded, industry,founderRepository.findById(founderId).get())
        );
    }

    @Override
    public Startup update(Long id, String name, Double valuation, Integer yearFounded, Industry industry, Long founderId) throws Exception {
        if(name == null || valuation == null || yearFounded == null || industry == null || founderId == null){
            throw new Exception("Invalid parameters.");
        }

        Startup startup = startupRepository.findById(id).orElseThrow();
        startup.setName(name);
        startup.setValuation(valuation);
        startup.setYearFounded(yearFounded);
        startup.setIndustry(industry);
        startup.setFounder(founderRepository.findById(founderId).orElseThrow());

        return startupRepository.save(startup);
    }

    @Override
    public Startup delete(Long id) throws Exception {
        if(id == null){
            throw new Exception("Invalid ID for Startup");
        }
        Startup startup = startupRepository.findById(id).orElseThrow();
        startupRepository.delete(startup);
        return null;
    }

    @Override
    public Startup deactivate(Long id) throws Exception {
        Startup startup = startupRepository.findById(id).orElseThrow();

        startup.setActive(!startup.isActive());

        return startupRepository.save(startup);
    }

    @Override
    public Page<Startup> findPage(String name, Double valuation, Integer yearFounded, Industry industry, Long founderId, int pageNum, int pageSize) {
        Specification<Startup> specification = Specification.allOf(
                filterContainsText(Startup.class, "name", name),
                filterGreaterThan(Startup.class, "valuation", valuation),
                filterGreaterThan(Startup.class, "yearFounded", yearFounded),
                filterEquals(Startup.class, "industry", industry),
                filterEquals(Startup.class, "founder.id", founderId)
        );

        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "name"));
        return this.startupRepository.findAll(
                specification,
                pageable);
    }
}
