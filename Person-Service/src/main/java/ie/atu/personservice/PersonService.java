package ie.atu.personservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
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
    public ResponseEntity<?> signInPerson(String name, String password) {
        Optional<Person> verifyPerson = personRepository.findByName(name);
        if(verifyPerson.isPresent()){
            Person existingPerson = verifyPerson.get();
            String verifyPassword = existingPerson.getPassword();
            if (verifyPassword.equals(password)) {
                return ResponseEntity.ok("Sign in successful");
            }else {
                //change to actual error handling
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match");
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account does not exist");
        }
    }

    //View profile
    public ResponseEntity<?> viewPersonProfile(String name, String password) {
        Optional<Person> verifyPerson = personRepository.findByName(name);
        if(verifyPerson.isPresent()){
            Person existingPerson = verifyPerson.get();
            String verifyPassword = existingPerson.getPassword();
            if (verifyPassword.equals(password)) {
                Map<String, String> user = new LinkedHashMap<>();
                user.put("Name", existingPerson.getName());
                user.put("Email", existingPerson.getEmail());
                user.put("Password", existingPerson.getPassword());
                user.put("Bank details", existingPerson.getBankDetails());
                return ResponseEntity.ok(user);
            }else {
                //change to actual error handling
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match");
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account does not exist");
        }

    }

    //edit profile
    public ResponseEntity<?> editPersonProfile(String name, String password, Person personEdit) {
        Optional<Person> verifyPerson = personRepository.findByName(name);
        if(verifyPerson.isPresent()){
            Person existingPerson = verifyPerson.get();
            String verifyPassword = existingPerson.getPassword();
            if (verifyPassword.equals(password)) {
                existingPerson.setName(personEdit.getName());
                existingPerson.setEmail(personEdit.getEmail());
                existingPerson.setPassword(personEdit.getPassword());
                existingPerson.setBankDetails(personEdit.getBankDetails());
                personRepository.save(existingPerson);
                return ResponseEntity.ok("Account edited successfully");

            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password does not match");
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account does not exist");
        }
    }

    //delete
    public void DeleteAccount(String name) {
        Optional<Person> person = personRepository.findByName(name);
        if(person.isPresent()){
            Person currentPerson = person.get();
            personRepository.delete(currentPerson);
        }
    }

    //take email and password and if correct, call other func passing the name
    @RabbitListener (queues = RabbitMQConfig.PERSON_QUEUE)
    public double myPortfolioValue(Map<String, String> userDetails) {
        String name = userDetails.get("name");
        String password = userDetails.get("password");
        Optional<Person> verifyPerson = personRepository.findByName(name);
        if(verifyPerson.isPresent()){
            Person existingPerson = verifyPerson.get();
            String verifyPassword = existingPerson.getPassword();
            if (verifyPassword.equals(password)) {
                return stockClient.stockFindVal(name);
            }else {
                return 0;
            }
        }else{
            return 0;
        }
    }

}
