package mk.ukim.finki.wp.jan2025g2.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.jan2025g2.model.ParkLocation;
import mk.ukim.finki.wp.jan2025g2.repository.ParkLocationRepository;
import mk.ukim.finki.wp.jan2025g2.service.ParkLocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ParkLocationServiceImpl implements ParkLocationService {
    private final ParkLocationRepository parkLocationRepository;
    @Override
    public ParkLocation findById(Long id) {
        return parkLocationRepository.findById(id).orElseThrow();
    }

    @Override
    public List<ParkLocation> listAll() {
        return parkLocationRepository.findAll();
    }

    @Override
    public ParkLocation create(String country, String continent) throws Exception {
        if(country == null || continent == null){
            throw new Exception("Invalid parameters");
        }
        return parkLocationRepository.save(new ParkLocation(country, continent));
    }
}
