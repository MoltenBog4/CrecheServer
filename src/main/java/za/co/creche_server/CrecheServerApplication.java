package za.co.creche_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"za.co.creche_server*"})
public class CrecheServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrecheServerApplication.class);
    }
}
