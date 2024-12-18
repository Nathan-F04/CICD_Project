package ie.atu.cicd_project;

import org.springframework.stereotype.Service;

@Service
public class StocksService {
    private final StocksRepository stocksRepository;
    private final StocksToValue stocksToValue;

    public StocksService(StocksRepository stocksRepository, StocksToValue stocksToValue) {
        this.stocksRepository = stocksRepository;
        this.stocksToValue = stocksToValue;
    }

    public Stocks returnStocksById(long id) {
        return stocksRepository.findBystockId(id);
    }

    public void returnByName(String name){
        Stocks stock =stocksRepository.findByName(name);
        int stockShares = stock.getStockShares();
        String stockName = stock.getStockName();
        stocksToValue.portfolioFromStockVal(stockShares, stockName);
    }

    //make a way to call method that finds by name make obj, get stock name and stock shares
    //this calls func passing the name and shares
}
