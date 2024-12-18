package ie.atu.cicd_project;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<Optional<Account>> returnAccBal(String name) {
        Optional<Account> account = accountRepository.findByName(name);
        if(account.isPresent()){
            return ResponseEntity.ok(account);
        }else{
            System.out.println("Name does not exist");
        }
        return null;
    }

    public ResponseEntity<Optional<Account>> returnAccStock(String name) {
        Optional<Account> account = accountRepository.findByName(name);
        if(account.isPresent()){
            //add logic for stock check stocks here
            return ResponseEntity.ok(account); // this is a line so i can push you can remove this
        }else{
            System.out.println("Name does not exist");
        }
        return null;
    }
}
