package mk.ukim.finki.wp.june2025g1.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.wp.june2025g1.model.Founder;
import mk.ukim.finki.wp.june2025g1.model.exceptions.InvalidFounderIdException;
import mk.ukim.finki.wp.june2025g1.repository.FounderRepository;
import mk.ukim.finki.wp.june2025g1.service.FounderService;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Service
@AllArgsConstructor
public class FounderServiceImpl implements FounderService {
    private final FounderRepository founderRepository;

    @Override
    public Founder findById(Long id) {
        return founderRepository.findById(id).get();
    }

    @Override
    public List<Founder> listAll() {
        return founderRepository.findAll();
    }

    @Override
    public Founder create(String name, String email) throws Exception {
        if(name == null || email == null){
            throw new Exception("Invalid paramaters");
        }
        return founderRepository.save(new Founder(name, email));
    }
}
