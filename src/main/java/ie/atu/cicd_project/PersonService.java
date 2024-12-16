package ie.atu.cicd_project;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void signInPerson(Person person) {
        Person testperson =  personRepository.findBypersonId(person.getPersonId());
        //getters for comparison if statements

    }
    //delete
    public void DeletePerson(Person person) {
        //delete person from db
        personRepository.delete(person);
    }
}
