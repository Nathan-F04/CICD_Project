package ie.atu.cicd_project;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository {
    Optional<Account> findByName(String name);
}
