package ie.atu.personservice;

import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
        return personService.signUpPerson(person);
    }
    //sign in
    @GetMapping("/signIn/{name}/{password}")
    public ResponseEntity<?> signIn(@PathVariable String name, @PathVariable String password) {
        return personService.signInPerson(name, password);
    }
    //get request to check details
    @GetMapping("/viewProfile/{name}/{password}")
    public ResponseEntity<?> viewProfile(@PathVariable String name, @PathVariable String password) {
        return personService.viewPersonProfile(name, password);
    }
    //Delete account
    @DeleteMapping("/removeAccount/{name}")
    public void RemoveAccount(@PathVariable String name){
        personService.DeleteAccount(name);
    }
    //update user details
    @PutMapping("/editProfile/{name}/{password}")
    public ResponseEntity<?> EditProfile( @PathVariable String name,  @PathVariable String password,  @RequestBody Person personEdit) {
        return personService.editPersonProfile(name, password, personEdit);
    }

    //enter a password and name
    //cal func-openfeign+mqtt
    @GetMapping("/PortfolioValue/{name}/{password}")
    public double portfolioValue( @PathVariable String name, @PathVariable String password){
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("name", name);
        userDetails.put("password", password);
        return (double) template.convertSendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, userDetails);
    }

    @GetMapping("/returnNames")
    public List<String> returnNames(){
        return personService.returnNames();
    }

}