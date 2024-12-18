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

    public ResponseEntity<Account> returnAccBal(String name) {
        Account account = accountRepository.findByName(name);
        return ResponseEntity.ok(account);
    }

    public ResponseEntity<Account> returnAccStock(String name) {
        Account account = accountRepository.findByName(name);
        return ResponseEntity.ok(account);
    }
}
