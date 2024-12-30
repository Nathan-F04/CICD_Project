package ie.atu.stockservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//on port 8082
@FeignClient(name="StockValue-Client-1", url="http://localhost:8083/stockValue")
public interface StockValueClient {

    @GetMapping("/getStockVal/{stockName}")
    double portfolioFromStockVal(@PathVariable String stockName);

    @GetMapping("/getAllStockNames")
    List<String> stockNames();
}
