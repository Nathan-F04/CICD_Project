package ie.atu.cicd_project;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;

    private final StocksRepository stocksRepository;

    private final StockValueRepository stockValueRepository;

    public DataLoader(PersonRepository personRepository, AccountRepository accountRepository, StocksRepository stocksRepository, StockValueRepository stockValueRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
        this.stocksRepository = stocksRepository;
        this.stockValueRepository = stockValueRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        //Insert data here
        //person
        Person testData1 = new Person("Paul" , "paul@atu.ie","pass1", "AIB", "1");
        Person testData2 = new Person("Nathan" , "nathan@atu.ie","pass2", "AIB", "2");
        Person testData3 = new Person("Shine", "shine@atu.ie","pass3", "AIB", "3");
        //account
        Account testAccount1 = new Account("Nathan" , 123);
        Account testAccount2 = new Account("Shine", 234);
        //stock value
        StockValue testStockValue1 = new StockValue(248.05, "Apple");
        StockValue testStockValue2 = new StockValue(220.52, "Amazon");
        StockValue testStockValue3 = new StockValue(128.91, "NVIDIA");
        StockValue testStockValue4 = new StockValue(1211.28, "Coke");
        //stocks
        Stocks testStock1 = new Stocks("Amazon", 150, "Shine");
        Stocks testStock2 = new Stocks("Apple", 100,"Nathan");
        Stocks testStock3 = new Stocks("NVIDIA", 70, "Shine");
        Stocks testStock4 = new Stocks("NVIDIA", 120,"Nathan");
        Stocks testStock5 = new Stocks("Apple", 180, "Shine");
        Stocks testStock6 = new Stocks("Coke", 90,"Nathan");

        //save here
        //people
        personRepository.save(testData1);
        personRepository.save(testData2);
        personRepository.save(testData3);
        //accounts
        accountRepository.save(testAccount1);
        accountRepository.save(testAccount2);
        //stock value
        stockValueRepository.save(testStockValue1);
        stockValueRepository.save(testStockValue2);
        stockValueRepository.save(testStockValue3);
        stockValueRepository.save(testStockValue4);
        //stock shares
        stocksRepository.save(testStock1);
        stocksRepository.save(testStock2);
        stocksRepository.save(testStock3);
        stocksRepository.save(testStock4);
        stocksRepository.save(testStock5);
        stocksRepository.save(testStock6);
        //delete here
        personRepository.delete(testData1);
    }
}
