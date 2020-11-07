package org.bubbasmith.vocabtivator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class VocabtivatorPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(VocabtivatorPracticeApplication.class, args);
    }

}
