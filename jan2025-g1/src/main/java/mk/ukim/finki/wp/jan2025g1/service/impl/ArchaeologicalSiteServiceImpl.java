package mk.ukim.finki.wp.jan2025g1.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.jan2025g1.model.ArchaeologicalSite;
import mk.ukim.finki.wp.jan2025g1.model.HistoricalPeriod;
import mk.ukim.finki.wp.jan2025g1.repository.ArchaeologicalSiteRepository;
import mk.ukim.finki.wp.jan2025g1.repository.SiteLocationRepository;
import mk.ukim.finki.wp.jan2025g1.service.ArchaeologicalSiteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.jan2025g1.service.FieldFilterSpecification.*;

@Service
@AllArgsConstructor
public class ArchaeologicalSiteServiceImpl implements ArchaeologicalSiteService {
    private final ArchaeologicalSiteRepository archaeologicalSiteRepository;
    private final SiteLocationRepository siteLocationRepository;
    @Override
    public List<ArchaeologicalSite> listAll() {
        return archaeologicalSiteRepository.findAll();
    }

    @Override
    public ArchaeologicalSite findById(Long id) {
        return archaeologicalSiteRepository.findById(id).orElseThrow();
    }

    @Override
    public ArchaeologicalSite create(String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId) {
        return archaeologicalSiteRepository.save(new ArchaeologicalSite(name, areaSize, rating, period, siteLocationRepository.findById(locationId).orElseThrow()));
    }

    @Override
    public ArchaeologicalSite update(Long id, String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId) {
        ArchaeologicalSite archaeologicalSite = archaeologicalSiteRepository.findById(id).orElseThrow();
        archaeologicalSite.setName(name);
        archaeologicalSite.setAreaSize(areaSize);
        archaeologicalSite.setRating(rating);
        archaeologicalSite.setPeriod(period);
        archaeologicalSite.setLocation(siteLocationRepository.findById(locationId).orElseThrow());
        return archaeologicalSiteRepository.save(archaeologicalSite);
    }

    @Override
    public ArchaeologicalSite delete(Long id) {
        archaeologicalSiteRepository.deleteById(id);
        return null;
    }

    @Override
    public ArchaeologicalSite close(Long id) {
        ArchaeologicalSite archaeologicalSite = archaeologicalSiteRepository.findById(id).orElseThrow();
        archaeologicalSite.setClosed(!archaeologicalSite.isClosed());
        return archaeologicalSiteRepository.save(archaeologicalSite);
    }

    @Override
    public Page<ArchaeologicalSite> findPage(String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId, int pageNum, int pageSize) {

        Specification<ArchaeologicalSite> specification = Specification.allOf(
                filterContainsText(ArchaeologicalSite.class, "name", name),
                greaterThan(ArchaeologicalSite.class, "areaSize", areaSize),
                greaterThan(ArchaeologicalSite.class, "rating", rating),
                filterEquals(ArchaeologicalSite.class, "period", period),
                filterEquals(ArchaeologicalSite.class, "location.id", locationId)
        );

        return this.archaeologicalSiteRepository.findAll(
                specification,
                PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "name"))
        );
    }
}
