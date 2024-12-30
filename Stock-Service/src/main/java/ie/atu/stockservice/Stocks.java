package ie.atu.stockservice;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stocks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "stockId can't be a null value.")
    private long stockId;
    @NotEmpty(message = "No stockName provided.")
    private String stockName;
    @NotNull(message = "stockShares can't be a null value.")
    private int stockShares;
    @NotEmpty(message = "No name provided.")
    private String name;

    public Stocks(String stockName, int stockShares, String name) {
        this.stockName = stockName;
        this.stockShares = stockShares;
        this.name = name;
    }
}
