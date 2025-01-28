package com.gamehouse.project;


import com.gamehouse.project.models.Age_Ratings;
import com.gamehouse.project.services.APICallService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@ComponentScan
public class ProjectApplication {

    public static void main(String[] args) throws Exception {
        //APICallService test = new APICallService();
        //String string = "god of war";
        //int id = 143393;
        //test.getGamesLight(string);
        //test.getGamesbyList(string);
        //test.getGamebyIDGBCODE(143393);
        //System.out.println(test.getGameRating(ratings));
      SpringApplication.run(ProjectApplication.class, args);
    }


}
