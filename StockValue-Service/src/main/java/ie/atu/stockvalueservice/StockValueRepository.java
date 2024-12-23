package ie.atu.stockvalueservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockValueRepository extends JpaRepository<StockValue, Long> {

    StockValue findByStockName (String stockName);
    StockValue findByValueId(int stockId);

    @Query("SELECT e.stockName FROM StockValue e")
    List<String> findAllColumns();
}
