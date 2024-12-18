package ie.atu.cicd_project;

public class StockValueService {

    private final StockValueRepository stockValueRepository;

    public StockValueService(StockValueRepository stockValueRepository) {
        this.stockValueRepository = stockValueRepository;
    }

    public float portfolioResult(int stockShares, String stockName){
        StockValue stockValue = stockValueRepository.findByStockName(stockName);
        return (stockName*stockValue.getStockShareValue());
    }
}
