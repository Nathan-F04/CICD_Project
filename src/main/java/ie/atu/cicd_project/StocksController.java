package ie.atu.cicd_project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StocksController {
    final private StocksService stocksService;

    public StocksController(StocksService stocksService) {
        this.stocksService = stocksService;
    }

    @GetMapping("/getStock/{id}")
    public ResponseEntity<Stocks> getStock(@PathVariable Long id) {
        Stocks stocks = stocksService.returnStocksById(id);
        return ResponseEntity.ok(stocks);
    }

    //func with person name here
    //make a way to call method that finds by name make obj, get stock name and stock shares


        @GetMapping("stockFindVal")
        public float stockFindVal(String name) {
            return stocksService.returnByName(name);

        }


}
