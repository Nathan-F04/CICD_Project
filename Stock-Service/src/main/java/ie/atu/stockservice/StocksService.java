package ie.atu.stockservice;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StocksService {
    private final StocksRepository stocksRepository;
    private final StockValueClient stockValueClient;

    private final PersonClient personClient;

    public StocksService(StocksRepository stocksRepository, StockValueClient stockValueClient, PersonClient personClient) {
        this.stocksRepository = stocksRepository;
        this.stockValueClient = stockValueClient;
        this.personClient = personClient;
    }

    public Map<String, Object> returnByName(String name){
        double total =0.0;
        Map<String, Object> response = new HashMap<>();
        List<Stocks> myStocks = stocksRepository.findAllByName(name);
        if(myStocks.isEmpty()){
            response.put("Message", "Error: No stocks found");
            response.put("Code", HttpStatus.BAD_REQUEST.value());
            return response;
        }else {
            for (Stocks stock : myStocks) {
                int stockShares = stock.getStockShares();
                String stockName = stock.getStockName();
                total = total + stockShares * stockValueClient.portfolioFromStockVal(stockName);
            }
            //return ResponseEntity.ok(total);
            response.put("Balance", total);
            response.put("Code", HttpStatus.OK.value());
            return response;
        }
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

    public void buy(String stock, int shares, String name){
        Optional<Stocks> stockObj = stocksRepository.findStocksByStockNameAndName(stock, name);
        if(stockObj.isPresent()){
            Stocks stockEdit = stockObj.get();
            int originalShares = stockEdit.getStockShares();
            stockEdit.setStockShares(originalShares+shares);
            stocksRepository.save(stockEdit);
        }
    }

    public void sell(String stock, int shares, String name){
        Optional<Stocks> stockObj = stocksRepository.findStocksByStockNameAndName(stock, name);
        if(stockObj.isPresent()){
            Stocks stockEdit = stockObj.get();
            int originalShares = stockEdit.getStockShares();
            stockEdit.setStockShares(originalShares-shares);
            stocksRepository.save(stockEdit);
        }
    }

    public int checkSharesService(String name, String stock){
        Optional<Stocks> stockEdit = stocksRepository.findStocksByStockNameAndName(stock, name);
        if(stockEdit.isPresent()){
            Stocks currentStock = stockEdit.get();
            return currentStock.getStockShares();
        }
        else{
            return 0;
        }
    }

    public void deleteStocks(String name) {
        List<Stocks> stockEdit = stocksRepository.findAllByName(name);
        if(!stockEdit.isEmpty()){
            stocksRepository.deleteAll(stockEdit);
        }
    }

    public void createNewCompanyStock(String stockName) {
        List<String> myPerson = personClient.returnNames();
        for(String myString: myPerson) {
            Stocks newStocks = new Stocks();
            newStocks.setStockName(stockName);
            newStocks.setStockShares(0);
            newStocks.setName(myString);
            stocksRepository.save(newStocks);
        }
    }

    public void swapStockDetailsService(String oldName, String newName){
        List<Stocks> myStockList = stocksRepository.findAllByName(oldName);

        for(Stocks myObj: myStockList) {
            myObj.setName(newName);
            stocksRepository.save(myObj);
        }
    }
}
