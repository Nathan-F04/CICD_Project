package ie.atu.stockvalueservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockValueRepository extends JpaRepository<StockValue, Long> {

    StockValue findByStockName (String stockName);
    Optional<StockValue> findByValueId(long valueId);
    @Query("SELECT e.stockName FROM StockValue e")
    List<String> findAllColumns();
}
