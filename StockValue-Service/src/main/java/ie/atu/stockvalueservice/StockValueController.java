package ie.atu.stockvalueservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/StockValue")
public class StockValueController {

    private final StockValueService stockValueService;

    public StockValueController(StockValueService stockValueService) {
        this.stockValueService = stockValueService;
    }

    @GetMapping("/getStockVal/{stockName}")
    public double portfolioFromStockVal(@PathVariable String stockName){
        return stockValueService.portfolioResult(stockName);
    }

    @GetMapping("/getStockName/{stockId}")
    public String stockNameById(@PathVariable int stockId) {
        return stockValueService.getStockName(stockId);
    }

    @GetMapping("/getAllStockNames")
    public List<String> stockNames() {
        return stockValueService.getColumnNames();
    }
}
