package ie.atu.accountservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="Stock-Client", url="http://localhost:8082/stock")
public interface StockClient {

    @GetMapping("/findStockVal/{name}")
    ResponseEntity<?> stockFindVal(@PathVariable String name);

    @PostMapping("/createNewStocks/{name}")
    void createNewStocks(@PathVariable String name);

    @PutMapping("/buyNewStocks/{name}/{stock}/{shares}")
    void buyNewStocks(@PathVariable String name, @PathVariable int shares, @PathVariable String stock);

    @PutMapping("/sellStocks/{name}/{stock}/{shares}")
    void sellStocks(@PathVariable String name, @PathVariable int shares, @PathVariable String stock);

    @GetMapping("/checkShares/{name}/{stock}")
    int checkShares(@PathVariable String name, @PathVariable String stock);

}