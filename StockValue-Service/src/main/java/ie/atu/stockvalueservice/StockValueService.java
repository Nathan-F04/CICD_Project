package ie.atu.stockvalueservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<?> getStock(int stockId) {
        Optional<StockValue> stockValue = stockValueRepository.findByValueId(stockId);
        if(stockValue.isPresent()){
            StockValue currentStockValue = stockValue.get();
            return ResponseEntity.ok(currentStockValue);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No stock found with the id: " + stockId);
        }

    }

    public List<String> getColumnNames() {
        return stockValueRepository.findAllColumns();
    }

    public ResponseEntity<?> addCompany(StockValue stockValue) {
        StockValue newCompany = new StockValue();
        newCompany.setStockName(stockValue.getStockName());
        newCompany.setStockShareValue(stockValue.getStockShareValue());
        stockValueRepository.save(newCompany);

    }
}
