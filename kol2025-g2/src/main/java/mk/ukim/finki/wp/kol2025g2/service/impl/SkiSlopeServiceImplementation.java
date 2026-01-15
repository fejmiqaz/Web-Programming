package mk.ukim.finki.wp.kol2025g2.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.kol2025g2.model.SkiSlope;
import mk.ukim.finki.wp.kol2025g2.model.SlopeDifficulty;
import mk.ukim.finki.wp.kol2025g2.repository.SkiResortRepository;
import mk.ukim.finki.wp.kol2025g2.repository.SkiSlopeRepository;
import mk.ukim.finki.wp.kol2025g2.service.SkiSlopeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;

import static mk.ukim.finki.wp.kol2025g2.service.FieldFilterSpecification.*;

@AllArgsConstructor
@Service
public class SkiSlopeServiceImplementation implements SkiSlopeService {
    private final SkiSlopeRepository skiSlopeRepository;
    private final SkiResortRepository skiResortRepository;

    @Override
    public List<SkiSlope> listAll() {
        return skiSlopeRepository.findAll();
    }

    @Override
    public SkiSlope findById(Long id) throws Exception {
        if(id == null){
            throw new Exception("SkiSlope doesn't exist.");
        }

        return skiSlopeRepository.findById(id).get();
    }

    @Override
    public SkiSlope create(String name, Integer length, SlopeDifficulty difficulty, Long skiResort) throws Exception {
        if(name == null || length == null || difficulty == null || skiResort == null){
            throw new Exception("Invalid parameters for creating.");
        }

        SkiSlope skiSlope = skiSlopeRepository.save(new SkiSlope(name, length, difficulty, skiResortRepository.findById(skiResort).get()));

        return skiSlope;
    }

    @Override
    public SkiSlope update(Long id, String name, Integer length, SlopeDifficulty difficulty, Long skiResort) throws Exception {
        if(name == null || length == null || difficulty == null || skiResort == null){
            throw new Exception("Invalid parameters for updating.");
        }

        SkiSlope skiSlope = skiSlopeRepository.findById(id).get();
        skiSlope.setName(name);
        skiSlope.setLength(length);
        skiSlope.setDifficulty(difficulty);
        skiSlope.setSkiResort(skiResortRepository.findById(skiResort).orElseThrow());

        return skiSlopeRepository.save(skiSlope);
    }

    @Override
    public SkiSlope delete(Long id) {
        skiSlopeRepository.delete(skiSlopeRepository.findById(id).get());
        return null;
    }

    @Override
    public SkiSlope close(Long id) {
        SkiSlope skiSlope = skiSlopeRepository.findById(id).get();
        skiSlope.setClosed(!skiSlope.isClosed());
        return skiSlopeRepository.save(skiSlope);
    }

    @Override
    public Page<SkiSlope> findPage(String name, Integer length, SlopeDifficulty difficulty, Long skiResort, int pageNum, int pageSize) {
        Specification<SkiSlope> specification = Specification.allOf(
                filterContainsText(SkiSlope.class, "name", name),
                greaterThan(SkiSlope.class, "length", length),
                filterEquals(SkiSlope.class,"difficulty", difficulty),
                filterEquals(SkiSlope.class, "skiResort.id", skiResort)
        );
        return this.skiSlopeRepository.findAll(
                specification,
                PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC,"name"))
        );
    }
}
