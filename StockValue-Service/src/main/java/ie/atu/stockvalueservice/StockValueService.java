package ie.atu.stockvalueservice;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockValueService {

    private final StockValueRepository stockValueRepository;

    public StockValueService(StockValueRepository stockValueRepository) {
        this.stockValueRepository = stockValueRepository;
    }

    public double portfolioResult(String stockName){
        StockValue stockValue = stockValueRepository.findByStockName(stockName);
        return stockValue.getStockShareValue();
    }

    public StockValue getStock(int stockId) {
        return stockValueRepository.findByStockId(stockId);
    }

    public List<String> getColumnNames() {
        return stockValueRepository.findAllColumns();
    }
}
