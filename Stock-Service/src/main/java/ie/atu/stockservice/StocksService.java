package ie.atu.stockservice;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StocksService {
    private final StocksRepository stocksRepository;
    private final StockValueClient stockValueClient;

    public StocksService(StocksRepository stocksRepository, StockValueClient stockValueClient) {
        this.stocksRepository = stocksRepository;
        this.stockValueClient = stockValueClient;
    }

    public Stocks returnStocksById(long id) {
        return stocksRepository.findBystockId(id);
    }

    public ResponseEntity<?> returnByName(String name){
        double total =0.0;
        List<Stocks> myStocks = stocksRepository.findAllByName(name);
        for(Stocks stock: myStocks){
            int stockShares = stock.getStockShares();
            String stockName = stock.getStockName();
            total = total + stockShares * stockValueClient.portfolioFromStockVal(stockName);
        }

        return ResponseEntity.ok(total);
    }

    public void createNewStock(String name) {
        List<String> myStocks = stockValueClient.stockNames();
        for(String myString: myStocks) {
            Stocks newStocks = new Stocks();
            newStocks.setStockName(myString);
            newStocks.setStockShares(0);
            newStocks.setName(name);
            stocksRepository.save(newStocks);
        }
    }
}
