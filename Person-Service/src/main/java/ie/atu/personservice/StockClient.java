package ie.atu.personservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

//on port 8081
@FeignClient(name="Stock-Client", url="http://localhost:8082/stock")
public interface StockClient {

    @GetMapping("/findStockVal/{name}")
    ResponseEntity<?> stockFindVal(@PathVariable String name);
}
