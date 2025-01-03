package ie.atu.accountservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long personId;
    @NotEmpty(message = "Name can not be empty")
    private String name;
    @Min(value = 0, message = "bankBal can't be less than 0")
    private float bankBal;

    public Account(String name, float bankBal) {
        this.name = name;
        this.bankBal = bankBal;
    }
}
