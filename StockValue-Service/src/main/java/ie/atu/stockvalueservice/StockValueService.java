package ie.atu.stockvalueservice;

import org.springframework.stereotype.Service;

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
}
