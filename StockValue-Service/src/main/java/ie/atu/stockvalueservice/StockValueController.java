package ie.atu.stockvalueservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("StockValue")
public class StockValueController {

    private final StockValueService stockValueService;

    public StockValueController(StockValueService stockValueService) {
        this.stockValueService = stockValueService;
    }

    @GetMapping("/getStockVal")
    public void portfolioFromStockVal(int stockShares, String stockName){
        stockValueService.portfolioResult(stockShares, stockName);
    }
}
