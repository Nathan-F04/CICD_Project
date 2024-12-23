package ie.atu.accountservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    private final PersonClient personClient;

    private final StockClient stockClient;

    public AccountService(AccountRepository accountRepository, PersonClient personClient, StockClient stockClient) {
        this.accountRepository = accountRepository;
        this.personClient = personClient;
        this.stockClient = stockClient;
    }

    public ResponseEntity<?> returnAccBal(String name) {
        Optional<Account> account = accountRepository.findByName(name);
        if(account.isPresent()) {
            return ResponseEntity.ok(account);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not Found");
        }
    }

    public ResponseEntity<?> returnAccStock(String name) {
        Optional<Account> account = accountRepository.findByName(name);
        return ResponseEntity.ok(account);
    }

    public ResponseEntity<?> addBal(String name, float bankBal) {
        Optional<Account> account = accountRepository.findByName(name);
        if(account.isPresent()) {
            Account accountCurrent = account.get();
            float bal = accountCurrent.getBankBal() + bankBal;
            accountCurrent.setBankBal(bal);
            accountRepository.save(accountCurrent);
            return ResponseEntity.ok(accountCurrent);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not Found");
        }
    }

    public double checkbal(String name){
        Optional<Account> account = accountRepository.findByName(name);
        if(account.isPresent()) {
            Account accountCurrent = account.get();
            return accountCurrent.getBankBal();
        }
        else {
            return 0;
        }
    }

    public void createAcc(String name) {
        Account account = new Account();
        account.setName(name);
        account.setBankBal(0);
        accountRepository.save(account);
    }

    public ResponseEntity<?> deleteAcc(String name) {
        Optional<Account> account = accountRepository.findByName(name);
        if(account.isPresent()){
            Account accountCurrent = account.get();
            accountRepository.delete(accountCurrent);
            personClient.RemoveAccount(accountCurrent.getName());
            //add a way to delete stocks here
            return ResponseEntity.ok("Account deleted successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not Found");
        }
    }

    //buy and sell stock
    public ResponseEntity<?> stockBuy (String stock, int amount, String name){
        //function to check balance and store it
        double bal = checkbal(name);
        //find stock price
        //int price = func(stock)
        //price * amount = total
        //if total>bal return invalid transaction else write it to the db and remove money from the account
    }
}
