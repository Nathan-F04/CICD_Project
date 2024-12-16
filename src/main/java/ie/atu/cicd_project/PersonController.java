package ie.atu.cicd_project;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<String>signUp(@Valid @RequestBody Person person) {
        personService.signUpPerson(person);
        return new ResponseEntity<>("Signed up successfully", HttpStatus.OK);
    }
    //sign in
    @GetMapping
    public ResponseEntity<String>signIn(@Valid @RequestBody Person person) {
        personService.signInPerson(person);
        return new ResponseEntity<>("Signed up successfully", HttpStatus.OK);
    }
    //get request to check details

    //Delete account
    @DeleteMapping("/removeAccount")
    public ResponseEntity<String>RemoveAccount(@Valid @RequestBody Person person){
        personService.DeletePerson(person);
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