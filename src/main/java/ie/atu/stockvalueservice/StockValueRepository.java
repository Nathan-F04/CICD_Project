package ie.atu.stockvalueservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockValueRepository extends JpaRepository<StockValue, Long> {

    StockValue findByStockName (String stockName);
}
