package ie.atu.stockvalueservice;

public class StockValueService {

    private final StockValueRepository stockValueRepository;

    public StockValueService(StockValueRepository stockValueRepository) {
        this.stockValueRepository = stockValueRepository;
    }

    public void portfolioResult(int stockShares, String stockName){
        StockValue stockValue = stockValueRepository.findByStockName(stockName);
        int ShareVal = stockValue.getStockShareValue();
        System.out.println((float) (ShareVal*stockShares));
    }
}
