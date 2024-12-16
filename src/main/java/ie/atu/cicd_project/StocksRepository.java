package ie.atu.cicd_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Long> {
    Stocks findSharesById(long id);
}
