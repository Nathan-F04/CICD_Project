package ie.atu.accountservice;

import org.springframework.http.HttpStatus;
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

    public void createAcc(String name) {
        Account account = new Account();
        account.setName(name);
        account.setBankBal(0);
        accountRepository.save(account);
    }
}
