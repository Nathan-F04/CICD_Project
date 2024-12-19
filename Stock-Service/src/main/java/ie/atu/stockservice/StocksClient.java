package ie.atu.stockservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//must be on port 8082
@FeignClient(name="StocksClient", url="http://localhost:8081/StockValue")
public interface StocksClient {

    @GetMapping("/getStockVal")
    void portfolioFromStockVal(@RequestParam("stockShares") int stockShares,
                               @RequestParam("stockName") String stockName);
}
