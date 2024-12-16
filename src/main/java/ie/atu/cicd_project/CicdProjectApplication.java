package ie.atu.cicd_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class CicdProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CicdProjectApplication.class, args);
    }

}
