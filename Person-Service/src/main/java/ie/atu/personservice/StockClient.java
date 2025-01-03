package ie.atu.personservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;

//on port 8081
@FeignClient(name="Stock-Client-1", url="http://localhost:8082/stock")
public interface StockClient {

    @GetMapping("/findStockVal/{name}")
    Map<String, Object> stockFindVal(@PathVariable String name);

    @PostMapping("/createNewStocks/{name}")
    void createNewStocks(@PathVariable String name);

    @PostMapping("/buyNewStocks/{name}/{stock}/{shares}")
    void buyNewStocks(@PathVariable String name, @PathVariable String stock, @PathVariable int shares);

    @PutMapping("/swapDetails/{oldName}/{newName}")
    void swapStockDetails(@PathVariable String oldName, @PathVariable String newName);
}
