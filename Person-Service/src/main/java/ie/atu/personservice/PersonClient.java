package ie.atu.personservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//stocks must be on 8081
@FeignClient(name="PersonClient", url="http://localhost:8081/stock")
public interface PersonClient {

    @GetMapping
    String stockFindVal(@RequestParam("name") String name);
}
