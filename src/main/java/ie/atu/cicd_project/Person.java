package ie.atu.cicd_project;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "You must add an id")
    private long personId;
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