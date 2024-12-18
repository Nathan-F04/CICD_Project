package ie.atu.cicd_project;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final PersonRepository personRepository;

    public DataLoader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        //Insert data here
        Person testData1 = new Person("Paul" , "paul@atu.ie","pass1", "AIB", "1");
        Person testData2 = new Person("Nathan" , "nathan@atu.ie","pass2", "AIB", "2");
        Person testData3 = new Person("Shine", "shine@atu.ie","pass3", "AIB", "3");
        //save here
        personRepository.save(testData1);
        personRepository.save(testData2);
        personRepository.save(testData3);
        //delete here
        personRepository.delete(testData1);
    }
}
