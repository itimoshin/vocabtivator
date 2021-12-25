package org.bubbasmith.vocabtivator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class VocabtivatorSentenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VocabtivatorSentenceApplication.class, args);
    }

}
