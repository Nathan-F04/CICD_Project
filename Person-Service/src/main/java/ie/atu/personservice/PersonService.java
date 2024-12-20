package ie.atu.personservice;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final StockClient stockClient;

    public PersonService(PersonRepository personRepository, StockClient stockClient) {
        this.personRepository = personRepository;
        this.stockClient = stockClient;
    }

    //Actually get the requests done here
    //sign up
    public void signUpPerson(Person person) {
        //save person to db to sign them up
        personRepository.save(person);
    }
    //sign in
    public void signInPerson(String name, String password) {
        Optional<Person> verifyPerson = personRepository.findByName(name);
        if(verifyPerson.isPresent()){
            Person existingPerson = verifyPerson.get();
            String verifyPassword = existingPerson.getPassword();
            if (verifyPassword.equals(password)) {
                System.out.println("Passwords match");
            }else {
                //change to actual error handling
                System.out.println("Passwords do not match");
            }
        }else{
            System.out.println("Person does not exist");
        }
    }

    //View profile
    public Person viewPersonProfile(String name, String password) {

        Optional<Person> verifyPerson = personRepository.findByName(name);
        if(verifyPerson.isPresent()){
            Person existingPerson = verifyPerson.get();
            String verifyPassword = existingPerson.getPassword();
            if (verifyPassword.equals(password)) {
                System.out.println("Passwords match");
                return existingPerson;
            }else {
                //change to actual error handling
                return null;
            }
        }else{
            return null;
        }
    }

    //edit profile
    public void editPersonProfile(String name, String password, Person personEdit) {
        Optional<Person> verifyPerson = personRepository.findByName(name);

        if(verifyPerson.isPresent()){
            Person existingPerson = verifyPerson.get();
            String verifyPassword = existingPerson.getPassword();
            if (verifyPassword.equals(password)) {
                System.out.println("Passwords match");
                existingPerson.setName(personEdit.getName());
                existingPerson.setEmail(personEdit.getEmail());
                existingPerson.setPassword(personEdit.getPassword());
                existingPerson.setStock_portfolio(personEdit.getStock_portfolio());
                existingPerson.setBankDetails(personEdit.getBankDetails());
                System.out.println("Person edited successfully");

            }else {
                //change to actual error handling
                System.out.println("Error editing person");
            }
        }else{
            System.out.println("Error editing person, person may not exist");
        }
    }

    //delete
    public void DeleteAccount(Person person) {
        //delete person from db
        personRepository.delete(person);
    }

    //take email and password and if correct, call other func passing the name
    public void myPortfolioValue(String name, String password) {
        Optional<Person> verifyPerson = personRepository.findByName(name);

        if(verifyPerson.isPresent()){
            Person existingPerson = verifyPerson.get();
            String verifyPassword = existingPerson.getPassword();
            if (verifyPassword.equals(password)) {
                System.out.println("Passwords match");
                stockClient.stockFindVal(name);

            }else {
                //change to actual error handling
                System.out.println("Error editing person");
            }
        }else{
            System.out.println("Error editing person, person may not exist");
        }
    }
}
