package ie.atu.cicd_project;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PersonController {

    //Person service DI
    private final PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
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
        return new ResponseEntity<>("Signed up successfully", HttpStatus.OK);
    }
    //get request to check details
    @GetMapping("/signIn/{name}/{password}")
    public ResponseEntity<?>viewProfile(@Valid @PathVariable String name, @Valid @PathVariable String password) {
        Person person = personService.viewPersonProfile(name, password);
        return ResponseEntity.ok(person);
    }
    //Delete account
    @DeleteMapping("/removeAccount")
    public ResponseEntity<?>RemoveAccount(@Valid @RequestBody Person person){
        personService.DeleteAccount(person);
        return new ResponseEntity<>("Deleted account successfully", HttpStatus.OK);
    }
    //update user details
    //put
    //change password
    //put
    //change email
    //put
    //change bank details
    //put
}
