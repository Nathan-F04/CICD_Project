package ie.atu.cicd_project;

import org.springframework.cloud.openfeign.FeignClient;

//stocks must be on 8081
@FeignClient(name="PersonToStocks", url="http://localhost:8081/stock/stockFindVal")
public interface PersonToStocks {

    String stockFindVal(String name);
}
