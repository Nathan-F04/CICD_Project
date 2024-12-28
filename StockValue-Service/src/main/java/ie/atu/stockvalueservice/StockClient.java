package ie.atu.stockvalueservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="Stock-Client-2", url="http://localhost:8082/stock")
public interface StockClient {
    @PostMapping("/createNewCompanyStock/{stockName}")
    void createNewCompanyStock(@PathVariable String stockName);
}
