package org.bubbasmith.vocabtivator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class VocabtivatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(VocabtivatorApplication.class, args);
    }

}
