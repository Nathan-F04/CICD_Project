package ie.atu.accountservice;

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
        return accountService.viewDetailsService(name);
    }

    @PutMapping("/increaseBal/{name}/{bankBal}")
    public ResponseEntity<?> increaseBal(@PathVariable String name, @PathVariable float bankBal) {
        return accountService.addBal(name, bankBal);
    }

    @PostMapping("/createAcc/{name}")
    public void createAcc(@PathVariable String name) {
        accountService.createAcc(name);
    }

    @DeleteMapping("/deleteAcc/{name}")
    public ResponseEntity<?> deleteAcc(@PathVariable String name) {
        return accountService.deleteAcc(name);
    }

    //buy and sell stocks
    @PutMapping("/buyStock/{stock}/{amount}/{name}")
    public ResponseEntity<?> buyStock(@PathVariable String stock, @PathVariable int amount, @PathVariable String name){
        return accountService.stockBuy(stock, amount, name);
    }
    @PutMapping("/sellStock/{stock}/{amount}/{name}")
    public ResponseEntity<?> SellStock(@PathVariable String stock, @PathVariable int amount, @PathVariable String name){
        return accountService.stockSell(stock, amount, name);
    }
}
