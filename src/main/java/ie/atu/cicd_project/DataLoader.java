package ie.atu.cicd_project;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;

    public DataLoader(PersonRepository personRepository, AccountRepository accountRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        //Insert data here
        Person testData1 = new Person("Paul" , "paul@atu.ie","pass1", "AIB", "1");
        Person testData2 = new Person("Nathan" , "nathan@atu.ie","pass2", "AIB", "2");
        Person testData3 = new Person("Shine", "shine@atu.ie","pass3", "AIB", "3");
        Account testAccount1 = new Account("Nathan" , 123);
        Account testAccount2 = new Account("Shine", 234);
        //save here
        personRepository.save(testData1);
        personRepository.save(testData2);
        personRepository.save(testData3);
        accountRepository.save(testAccount1);
        accountRepository.save(testAccount2);
        //delete here
        personRepository.delete(testData1);
    }
}
