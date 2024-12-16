package ie.atu.cicd_project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class Value {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "You must add a value id")
    private long valueId;
    @PositiveOrZero(message = "Stock value must be greater than zero")
    private int stockShareValue;
    @NotNull(message = "You must add a stock id")
    private long stockId;
}
