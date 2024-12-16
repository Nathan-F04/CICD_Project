package ie.atu.cicd_project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "You must add an id")
    private Long personId;
    @NotNull(message = "Name must not be blank")
    private String name;
    @Email(message = "Email must be in valid format")
    private String email;
    @NotNull(message = "Password must not be blank")
    private String password;
    @NotNull(message = "Bank details must not be blank")
    private String bankDetails;
    @PositiveOrZero(message = "Stock portfolio must be positive")
    private String stock_portfolio;
}
