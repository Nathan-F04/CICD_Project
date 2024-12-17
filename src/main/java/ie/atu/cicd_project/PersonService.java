package ie.atu.cicd_project;

import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //Actually get the requests done here
    //sign up
    public void signUpPerson(Person person) {
        //save person to db to sign them up
        personRepository.save(person);
    }
    //sign in
    public void signInPerson(long id, String password) {
        Person verifyPerson = personRepository.findBypersonId(id);
        String verifyPassword = verifyPerson.getPassword();
        if (verifyPassword.equals(password)) {
            System.out.println("Passwords match");
        }
    }

    //delete
    public void DeleteAccount(Person person) {
        //delete person from db
        personRepository.delete(person);
    }
}
