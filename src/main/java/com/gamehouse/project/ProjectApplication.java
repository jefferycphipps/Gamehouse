package com.gamehouse.project;

import com.gamehouse.project.services.APICallService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
public class ProjectApplication {

    public static void main(String[] args) throws Exception {
        //APICallService test = new APICallService();
//        long gameID = 251833;
//        test.getGamebyIDGBCODE(gameID);
       //System.out.println(test.saveGameGenres());
      SpringApplication.run(ProjectApplication.class, args);
    }

}
