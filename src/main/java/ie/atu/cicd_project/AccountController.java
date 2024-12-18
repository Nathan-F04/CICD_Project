package ie.atu.cicd_project;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(accountService.returnAccStock(name), HttpStatus.OK); //also a line, so I can push you can change this
    }

    @PutMapping("/increaseBal/{name}/{bankBal}")
    public ResponseEntity<?> increaseBal(@PathVariable String name, @PathVariable float bankBal) {
        return accountService.addBal(name, bankBal);
    }


}
