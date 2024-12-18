package ie.atu.cicd_project;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//must be on port 8082
@FeignClient(name="StocksToValue", url="http://localhost:8081/StockValue")
public interface StocksToValue {

    @GetMapping("/getStockVal")
    void portfolioFromStockVal(@RequestParam("stockShares") int stockShares,
                               @RequestParam("stockName") String stockName);
}
