package ie.atu.personservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PersonDataLoader implements CommandLineRunner {
    private final PersonRepository personRepository;

    public PersonDataLoader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        //Insert data here
        Person testData1 = new Person("Paul" , "paul@atu.ie","pass1", "AIB", "1");
        Person testData2 = new Person("Nathan" , "nathan@atu.ie","pass2", "AIB", "2");
        Person testData3 = new Person("Shine", "shine@atu.ie","pass3", "BOI", "3");
        Person testData4 = new Person("Bill", "bill@atu.ie","pass4", "BOI", "4");
        //save here
        personRepository.save(testData1);
        personRepository.save(testData2);
        personRepository.save(testData3);
        personRepository.save(testData4);
        //delete here
        personRepository.delete(testData1);
    }
}
