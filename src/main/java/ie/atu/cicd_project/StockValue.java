package ie.atu.cicd_project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
//@Table
public class StockValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "You must add a value id")
    private long valueId;
    @PositiveOrZero(message = "Stock value must be greater than zero")
    private double stockShareValue;
    @NotNull(message = "You must add a stock id")
    private String stockName;

    //constructor for data loader
    public StockValue(double stockShareValue, String stockName) {
        this.stockShareValue = stockShareValue;
        this.stockName = stockName;
    }
}
