package ie.atu.cicd_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    //methods below
    //Person findByName(String name);
    Optional<Person> findByName(String name);
}
