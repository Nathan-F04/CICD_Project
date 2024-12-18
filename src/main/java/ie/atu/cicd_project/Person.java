package ie.atu.cicd_project;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   //@NotNull(message = "Id must not be blank")
    private Long personId;
    @NotEmpty(message = "Name must not be blank")
    private String name;
    @Email(message = "Email must be in valid format")
    private String email;
    @NotEmpty(message = "Password must not be blank")
    private String password;
    @NotEmpty(message = "Bank details must not be blank")
    private String bankDetails;
    @PositiveOrZero(message = "Stock portfolio must be positive")
    private String stock_portfolio;

    //Constructor for data loader
    public Person(String name, String email, String password, String bankDetails, String stock_portfolio) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.bankDetails = bankDetails;
        this.stock_portfolio = stock_portfolio;
    }
}