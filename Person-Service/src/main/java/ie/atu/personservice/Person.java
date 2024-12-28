package ie.atu.personservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Email must not be blank")
    @Email(message = "Email must be in valid format")
    private String email;
    @NotEmpty(message = "Password must not be blank")
    private String password;
    @NotEmpty(message = "Bank details must not be blank")
    private String bankDetails;

    //Constructor for data loader
    public Person(String name, String email, String password, String bankDetails) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.bankDetails = bankDetails;
    }
}
