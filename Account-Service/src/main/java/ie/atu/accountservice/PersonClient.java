package ie.atu.accountservice;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="PersonClient1", url="http://localhost:8081/person")
public interface PersonClient {
    @DeleteMapping("/removeAccount/{name}")
    void RemoveAccount(@Valid @PathVariable String name);
}
