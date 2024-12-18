package ie.atu.cicd_project;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //used to view account balance and other details
    @GetMapping("/view/{name}")
    public ResponseEntity<?> viewDetails(@PathVariable String name) {
        return accountService.returnAccBal(name);
    }

    //used to view stocks
    @GetMapping("/view/stocksOwned/{name}")
    public ResponseEntity<?> viewStocksOwned(@PathVariable String name) {
        //add a way to display stock details
        return new ResponseEntity<>(accountService.returnAccStock(name), HttpStatus.OK); //also a line so i can push you can change this
    }


}
