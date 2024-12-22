package ie.atu.stockservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Long> {
    Stocks findBystockId(long id);
    List<Stocks> findAllByName(String name);
}
