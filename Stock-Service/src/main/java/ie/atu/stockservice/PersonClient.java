package ie.atu.stockservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="PersonClient2", url="http://localhost:8081/person")
public interface PersonClient {
    @GetMapping("/returnNames")
    List<String> returnNames();
}
