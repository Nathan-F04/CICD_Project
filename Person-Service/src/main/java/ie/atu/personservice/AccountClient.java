package ie.atu.personservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="Account-Client-1", url="http://localhost:8080/account")
public interface AccountClient {
    @PostMapping("/createAcc/{name}")
    void createAcc(@PathVariable String name);
}
