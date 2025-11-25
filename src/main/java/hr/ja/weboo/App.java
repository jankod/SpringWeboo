package hr.ja.weboo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        log.debug("http://localhost:8080/");
        SpringApplication.run(App.class, args);
    }

}
