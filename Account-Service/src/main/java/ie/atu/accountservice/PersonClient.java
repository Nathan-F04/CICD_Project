package ie.atu.accountservice;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name="PersonClient1", url="http://localhost:8081/person")
public interface PersonClient {
    @DeleteMapping("/removeAccount/{name}")
    void RemoveAccount(@Valid @PathVariable String name);

    @GetMapping("/portfolioValue/{name}/{password}")
    Map<String, Object> portfolioValue(@PathVariable String name, @PathVariable String password);
}
