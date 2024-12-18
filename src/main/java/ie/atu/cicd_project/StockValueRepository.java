package ie.atu.cicd_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockValueRepository extends JpaRepository<StockValue, Long> {

    StockValue stockvalue findByStockName(String stockName);
}
