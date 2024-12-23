package ie.atu.stockservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/findStockVal/{name}")
    public ResponseEntity<?> stockFindVal(@PathVariable String name) {
        return stocksService.returnByName(name);
    }

    @PostMapping("/createNewStocks/{name}")
    public void createNewStocks(@PathVariable String name) {
        stocksService.createNewStock(name);
    }

    @PostMapping("/buyNewStocks/{name}/{stock}/{shares}")
    public void buyNewStocks(@PathVariable String name, @PathVariable String stock, @PathVariable int shares){
        stocksService.buy(name, stock, shares);
    }


}
