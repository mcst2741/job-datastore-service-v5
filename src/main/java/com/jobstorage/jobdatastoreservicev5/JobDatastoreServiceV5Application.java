package com.jobstorage.jobdatastoreservicev5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;


@SpringBootApplication

public class JobDatastoreServiceV5Application {

    public static void main(String[] args) {
        SpringApplication.run(JobDatastoreServiceV5Application.class, args);
    }
/*
@Autowired
private Environment environment;
public String getServerPort(){
    return environment.getProperty("server.port");
}*/

}
