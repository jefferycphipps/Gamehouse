package com.gamehouse.project;

import com.gamehouse.project.services.APICallService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication()
public class ProjectApplication {

    public static void main(String[] args) {
//        APICallService test = new APICallService();
//        test.searchGames("God of War");
        SpringApplication.run(ProjectApplication.class, args);
    }

}
