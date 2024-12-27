package ie.atu.stockservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Long> {
    Stocks findBystockId(long id);

    Optional<Stocks> findStocksByStockNameAndName(String stockName, String name);
    List<Stocks> findAllByName(String name);
}
