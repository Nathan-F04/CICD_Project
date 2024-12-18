package ie.atu.cicd_project;

import jakarta.validation.constraints.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<?> returnAccBal(String name) {
        Account account = accountRepository.findByName(name);
        if(account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not Found");
        }
        else {
            return ResponseEntity.ok(account);
        }
    }

    public ResponseEntity<Account> returnAccStock(String name) {
        Account account = accountRepository.findByName(name);
        return ResponseEntity.ok(account);
    }
}
