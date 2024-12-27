package ie.atu.personservice;

import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/person")
public class PersonController {
    //Person service DI
    private final PersonService personService;
    private final RabbitTemplate template;

    public PersonController(PersonService personService, RabbitTemplate template) {
        this.personService = personService;
        this.template = template;
    }

    //Listen for requests and calls the following methods:
    //sign up
    @PostMapping("/signUp")
    public ResponseEntity<?>signUp(@Valid @RequestBody Person person) {
        personService.signUpPerson(person);
        return new ResponseEntity<>("Signed up successfully", HttpStatus.OK);
    }
    //sign in
    @GetMapping("/signIn/{name}/{password}")
    public ResponseEntity<?>signIn(@Valid @PathVariable String name, @Valid @PathVariable String password) {
        personService.signInPerson(name, password);
        return new ResponseEntity<>("Signed in successfully", HttpStatus.OK);
    }
    //get request to check details
    @GetMapping("/viewProfile/{name}/{password}")
    public ResponseEntity<?>viewProfile(@Valid @PathVariable String name, @Valid @PathVariable String password) {
        Person person = personService.viewPersonProfile(name, password);
        return ResponseEntity.ok(person);
    }
    //Delete account
    @DeleteMapping("/removeAccount/{name}")
    public void RemoveAccount(@Valid @PathVariable String name){
        personService.DeleteAccount(name);
    }
    //update user details
    @PutMapping("/editProfile/{name}/{password}")
    public ResponseEntity<?>EditProfile(@Valid @PathVariable String name, @Valid @PathVariable String password, @Valid @RequestBody Person personEdit) {
        personService.editPersonProfile(name, password, personEdit);
        return new ResponseEntity<>("Edited account successfully", HttpStatus.OK);
    }

    //enter a password and name
    //cal func-openfeign+mqtt
    @GetMapping("/PortfolioValue/{name}/{password}")
    public ResponseEntity<?> portfolioValue(@Valid @PathVariable String name, @Valid @PathVariable String password){
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("name", name);
        userDetails.put("password", password);
        return (ResponseEntity<?>) template.convertSendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, userDetails);
    }

}