package ie.atu.cicd_project;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Add entity and table
public class Stocks {
    //set primary key
    @NotNull(message = "stockId can't be a null value.")
    private long stockId;
    @NotEmpty(message = "stockName can't be empty.")
    private String stockName;
    @NotNull(message = "stockShares can't be a null value.")
    private int stockShares;
    @NotNull(message = "personId can't be a null value.")
    private long personId;
}
