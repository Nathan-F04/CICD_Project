package ie.atu.personservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final StockClient stockClient;

    private final AccountClient accountClient;

    public PersonService(PersonRepository personRepository, StockClient stockClient, AccountClient accountClient) {
        this.personRepository = personRepository;
        this.stockClient = stockClient;
        this.accountClient = accountClient;
    }

    //Actually get the requests done here
    //sign up
    public void signUpPerson(Person person) {
        //save person to db to sign them up
        personRepository.save(person);
        stockClient.createNewStocks(person.getName());
        accountClient.createAcc(person.getName());
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
                existingPerson.setStockPortfolio(personEdit.getStockPortfolio());
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
    public ResponseEntity<?> DeleteAccount(String name) {
        Optional<Person> person = personRepository.findByName(name);
        if(person.isPresent()){
            Person currentPerson = person.get();
            personRepository.delete(currentPerson);
            return ResponseEntity.ok("Deleted account successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not Found");
        }
    }

    //take email and password and if correct, call other func passing the name
    @RabbitListener (queues = RabbitMQConfig.PERSON_QUEUE)
    public ResponseEntity<?> myPortfolioValue(Map<String, String> userDetails) {
        String name =userDetails.get("name");
        String password = userDetails.get("password");
        Optional<Person> verifyPerson = personRepository.findByName(name);

        if(verifyPerson.isPresent()){
            Person existingPerson = verifyPerson.get();
            String verifyPassword = existingPerson.getPassword();
            if (verifyPassword.equals(password)) {
                System.out.println("Passwords match");
                return stockClient.stockFindVal(name);


            }else {
                //change to actual error handling
                System.out.println("Error editing person");
            }
        }else{
            System.out.println("Error editing person, person may not exist");
        }
        return (ResponseEntity<?>) ResponseEntity.status(400);
    }

}
