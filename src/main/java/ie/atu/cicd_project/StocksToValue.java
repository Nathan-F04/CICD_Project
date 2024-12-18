package ie.atu.cicd_project;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//must be on port 8082
@FeignClient(name="PersonToStocks", url="http://localhost:8081/StockValue")
public interface StocksToValue {
    @GetMapping
    public String portfolioFromStockVal(int stockShares, String stockName);



}
