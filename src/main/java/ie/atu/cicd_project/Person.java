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
    Long person_Id;
    @NotNull(message = "Name must not be blank")
    String Name;
    @Email(message = "Email must be in valid format")
    String Email;
    @NotNull(message = "Password must not be blank")
    String Password;
    @NotNull(message = "Bank details must not be blank")
    String Bank_details;
    @PositiveOrZero(message = "Stock portfolio must be positive")
    String Stock_portfolio;

}
