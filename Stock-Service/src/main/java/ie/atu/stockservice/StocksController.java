package ie.atu.stockservice;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/stock")
public class StocksController {
    final private StocksService stocksService;

    public StocksController(StocksService stocksService) {
        this.stocksService = stocksService;
    }

    //func with person name here
    //make a way to call method that finds by name make obj, get stock name and stock shares
    @GetMapping("/findStockVal/{name}")
    public Map<String, Object> stockFindVal(@PathVariable String name) {
        return stocksService.returnByName(name);
    }

    @PostMapping("/createNewStocks/{name}")
    public void createNewStocks(@PathVariable String name) {
        stocksService.createNewStock(name);
    }

    @PostMapping("/createNewCompanyStock/{stockName}")
    public void createNewCompanyStock(@PathVariable String stockName) {
        stocksService.createNewCompanyStock(stockName);
    }

    @PutMapping("/buyNewStocks/{name}/{stock}/{shares}")
    public void buyNewStocks(@PathVariable String name, @PathVariable int shares, @PathVariable String stock){
        stocksService.buy(stock, shares, name);
    }

    @PutMapping("/sellStocks/{name}/{stock}/{shares}")
    public void sellStocks(@PathVariable String name, @PathVariable int shares, @PathVariable String stock){
        stocksService.sell(stock, shares, name);
    }

    @GetMapping("/checkShares/{name}/{stock}")
    int checkShares(@PathVariable String name, @PathVariable String stock){
        return stocksService.checkSharesService(name,stock);
    }

    @DeleteMapping("/deleteStocks/{name}")
    public void deleteStocks(@PathVariable String name){
        stocksService.deleteStocks(name);
    }

    @PutMapping("/swapDetails/{oldName}/{newName}")
    public void swapStockDetails(@PathVariable String oldName, @PathVariable String newName){
        stocksService.swapStockDetailsService(oldName, newName);
    }

}
