package ie.atu.cicd_project;

import org.springframework.stereotype.Service;

@Service
public class StocksService {
    private final StocksRepository stocksRepository;

    public StocksService(StocksRepository stocksRepository) {
        this.stocksRepository = stocksRepository;
    }

    public Stocks returnStocksById(Long id) {
        return stocksRepository.findSharesBystockId(id);
    }
}
