package ie.atu.stockvalueservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StockValueDataLoader implements CommandLineRunner {
    private final StockValueRepository stockValueRepository;

    public StockValueDataLoader(StockValueRepository stockValueRepository) {
        this.stockValueRepository = stockValueRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        //Insert data here
        StockValue testData1 = new StockValue( 100,"Apple");
        StockValue testData2 = new StockValue(200, "MicroSoft");
        //save here
        stockValueRepository.save(testData1);
        stockValueRepository.save(testData2);
    }
}
