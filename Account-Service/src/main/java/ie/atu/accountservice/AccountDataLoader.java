package ie.atu.accountservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountDataLoader implements CommandLineRunner {
    private final AccountRepository accountRepository;

    public AccountDataLoader(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        //Insert data here
        Account testAccount1 = new Account("Nathan" , 123);
        Account testAccount2 = new Account("Shine", 234);
        //save here
        accountRepository.save(testAccount1);
        accountRepository.save(testAccount2);
    }
}
