package io.falcon.assignment;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


// @SpringBootApplication
// public class Application {

//     public static void main(String[] args) throws Exception {
//         SpringApplication.run(Application.class, args);
//     }
// }

@SpringBootApplication
@EnableJpaAuditing
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8080")); 
        app.run(args);
    }
}