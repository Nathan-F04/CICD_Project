package ie.atu.stockservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StockDataLoader implements CommandLineRunner {
    private final StocksRepository stocksRepository;

    public StockDataLoader(StocksRepository stocksRepository) {
        this.stocksRepository = stocksRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        //Insert data here
        Stocks testData1 = new Stocks("Apple", 1,"Nathan");
        Stocks testData2 = new Stocks("MicroSoft", 2, "Shine");
        //save here
        stocksRepository.save(testData1);
        stocksRepository.save(testData2);
    }
}
