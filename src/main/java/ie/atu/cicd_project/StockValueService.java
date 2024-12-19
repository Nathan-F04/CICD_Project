package ie.atu.cicd_project;

public class StockValueService {

    private final StockValueRepository stockValueRepository;

    public StockValueService(StockValueRepository stockValueRepository) {
        this.stockValueRepository = stockValueRepository;
    }

    public void portfolioResult(int stockShares, String stockName){
        StockValue stockValue = stockValueRepository.findByStockName(stockName);
        double ShareVal = stockValue.getStockShareValue();
        System.out.println((float) (ShareVal*stockShares));
    }
}
