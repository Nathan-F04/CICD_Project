package ie.atu.accountservice;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="PersonClient1", url="http://localhost:8081/person")
public interface PersonClient {
    @DeleteMapping("/removeAccount/{name}")
    ResponseEntity<?> RemoveAccount(@Valid @PathVariable String name);
}
