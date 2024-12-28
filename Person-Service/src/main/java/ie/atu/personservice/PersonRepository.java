package ie.atu.personservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    //methods below
    Optional<Person> findByName(String name);
    Optional<Person> findByEmail(String email);
    @Query("SELECT e.name FROM Person e")
    List<String> findAllColumns();
}
