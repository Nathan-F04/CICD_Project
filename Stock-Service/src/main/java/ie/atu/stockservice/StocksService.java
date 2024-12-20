package ie.atu.stockservice;

import org.springframework.stereotype.Service;

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

    public void returnByName(String name){
        Stocks stock = stocksRepository.findByName(name);
        int stockShares = stock.getStockShares();
        String stockName = stock.getStockName();
        System.out.println(stockShares * stockValueClient.portfolioFromStockVal(stockName));
    }

    //make a way to call method that finds by name make obj, get stock name and stock shares
    //this calls func passing the name and shares
}
