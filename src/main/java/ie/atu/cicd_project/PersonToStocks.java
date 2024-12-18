package ie.atu.cicd_project;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//stocks must be on 8081
@FeignClient(name="PersonToStocks", url="http://localhost:8081/stock")
public interface PersonToStocks {

    @GetMapping
    String stockFindVal(@RequestParam("name") String name);
}
