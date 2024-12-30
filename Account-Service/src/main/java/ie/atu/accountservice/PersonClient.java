package ie.atu.accountservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name="Person-Client-1", url="http://localhost:8081/person")
public interface PersonClient {
    @DeleteMapping("/removeAccount/{name}")
    void RemoveAccount(@PathVariable String name);

    @GetMapping("/portfolioValue/{name}/{password}")
    Map<String, Object> portfolioValue(@PathVariable String name, @PathVariable String password);
}
