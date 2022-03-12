package com.incs.crudapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.incs.crudapplication.Common")
@SpringBootApplication
public class CrudapplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudapplicationApplication.class, args);
    }

}
