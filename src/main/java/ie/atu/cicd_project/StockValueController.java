package ie.atu.cicd_project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("StockValue")
public class StockValueController {

    private final StockValueRepository stockValueRepository;

    public StockValueController(StockValueRepository stockValueRepository) {
        this.stockValueRepository = stockValueRepository;
    }

    @GetMapping
    public float portfolioFromStockVal(int stockShares, String stockName){
        return stockValueRepository.portfolioResult(stockShares, stockName);
    }
}
