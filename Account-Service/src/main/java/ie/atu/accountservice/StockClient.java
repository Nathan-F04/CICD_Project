package ie.atu.accountservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="Stock-Client", url="http://localhost:8082/stock")
public interface StockClient {

    @GetMapping("/findStockVal/{name}")
    ResponseEntity<?> stockFindVal(@PathVariable String name);

    @PostMapping("/createNewStocks/{name}")
    void createNewStocks(@PathVariable String name);
}