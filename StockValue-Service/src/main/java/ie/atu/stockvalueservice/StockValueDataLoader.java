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
        StockValue testData1 = new StockValue( 254.49,"Apple");
        StockValue testData2 = new StockValue(436.60, "Microsoft");
        StockValue testData3 = new StockValue(224.92 , "Amazon");
        StockValue testData4 = new StockValue(134.70 , "NVIDIA");
        StockValue testData5 = new StockValue(421.06 , "Tesla");
        StockValue testData6 = new StockValue(62.55 , "Coke");
        //save here
        stockValueRepository.save(testData1);
        stockValueRepository.save(testData2);
        stockValueRepository.save(testData3);
        stockValueRepository.save(testData4);
        stockValueRepository.save(testData5);
        stockValueRepository.save(testData6);
    }
}
