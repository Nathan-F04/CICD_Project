package ie.atu.personservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    //methods below
    Optional<Person> findByName(String name);
    Optional<Person> findByEmail(String email);
}
