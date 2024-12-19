package ie.atu.stockservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Long> {
    Stocks findBystockId(long id);
    Stocks findByName(String name);
}
