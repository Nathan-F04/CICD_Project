package ie.atu.stockservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//on port 8082
@FeignClient(name="StockValue-Client", url="http://localhost:8083/StockValue")
public interface StockValueClient {

    @GetMapping("/getStockVal/{name}")
    int portfolioFromStockVal(@PathVariable String name);

    @GetMapping("/getAllStockNames")
    List<String> stockNames();
}
