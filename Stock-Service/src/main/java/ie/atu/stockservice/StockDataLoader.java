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
        Stocks testData1 = new Stocks("Apple", 80,"Nathan");
        Stocks testData2 = new Stocks("Apple", 120,"Shine");
        Stocks testData3 = new Stocks("NVIDIA", 180,"Nathan");
        Stocks testData4 = new Stocks("Amazon", 230,"Shine");
        Stocks testData5 = new Stocks("Tesla", 50,"Shine");
        Stocks testData6 = new Stocks("Coke", 20,"Nathan");
        Stocks testData7 = new Stocks("Microsoft", 20, "Bill");
        Stocks testData8 = new Stocks("Amazon", 320, "Bill");
        Stocks testData9 = new Stocks("Coke", 130, "Bill");
        Stocks testData10 = new Stocks("Apple", 105, "Bill");
        Stocks testData11 = new Stocks("Tesla", 67, "Bill");
        //save here
        stocksRepository.save(testData1);
        stocksRepository.save(testData2);
        stocksRepository.save(testData3);
        stocksRepository.save(testData4);
        stocksRepository.save(testData5);
        stocksRepository.save(testData6);
        stocksRepository.save(testData7);
        stocksRepository.save(testData8);
        stocksRepository.save(testData9);
        stocksRepository.save(testData10);
        stocksRepository.save(testData11);
    }
}
