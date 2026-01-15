package mk.ukim.finki.wp.june2025g1.repository;

import mk.ukim.finki.wp.june2025g1.model.Founder;
import mk.ukim.finki.wp.june2025g1.model.Startup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StartupRepository extends JpaRepository<Startup, Long>, JpaSpecificationExecutor<Startup> {
    Founder founder(Founder founder);
}
