package ie.atu.stockvalueservice;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stockValue")
public class StockValueController {

    private final StockValueService stockValueService;

    public StockValueController(StockValueService stockValueService) {
        this.stockValueService = stockValueService;
    }

    @GetMapping("/getStockVal/{stockName}")
    public double portfolioFromStockVal(@PathVariable String stockName){
        return stockValueService.portfolioResult(stockName);
    }

    @GetMapping("/getStockName/{valueId}")
    public ResponseEntity<?> stockNameById(@PathVariable long valueId) {
        return stockValueService.getStock(valueId);
    }

    @GetMapping("/getAllStockNames")
    public List<String> stockNames() {
        return stockValueService.getColumnNames();
    }

    @PostMapping("/addCompany")
    public ResponseEntity<?> addCompany(@Valid @RequestBody StockValue stockValue) {
        return stockValueService.addCompany(stockValue);
    }
}
