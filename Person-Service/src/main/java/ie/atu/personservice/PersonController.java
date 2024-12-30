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
    public ResponseEntity<?> EditProfile( @PathVariable String name,  @PathVariable String password,  @Valid @RequestBody Person personEdit) {
        return personService.editPersonProfile(name, password, personEdit);
    }

    //enter a password and name
    //cal func-openfeign+mqtt
    @GetMapping("/portfolioValue/{name}/{password}")
    public Map<String, Object> portfolioValue(@PathVariable String name, @PathVariable String password) throws IllegalStateException {
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("name", name);
        userDetails.put("password", password);

        Object response = template.convertSendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, userDetails);

        if (response instanceof Map<?, ?>) {
            // Verify the types of the keys and values if necessary
            try {
                @SuppressWarnings("unchecked") // Suppress unchecked cast warning
                Map<String, Object> typedResponse = (Map<String, Object>) response;
                return typedResponse;
            } catch (ClassCastException e) {
                throw new IllegalStateException("Invalid response format: expected Map<String, Object>", e);
            }
        } else {
            throw new IllegalStateException("Invalid response type: expected a Map but got " + response.getClass());
        }
    }
    @GetMapping("/returnNames")
    public List<String> returnNames(){
        return personService.returnNames();
    }
}