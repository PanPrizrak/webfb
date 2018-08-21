package com.example.webfb.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.webfb.*;

import java.io.*;
import java.util.Properties;

@SpringBootApplication
public class Application {
    @Value("${upload.path}")
    private static String uploadPath;

    public static void main(String[] args){

        SpringApplication.run(Application.class, args);

        Properties prop = new Properties();
        OutputStream output = null;
        System.out.println(uploadPath);
        try {

            output = new FileOutputStream("src/main/resources/application.properties");
            String resourcesPath = new File(".").getAbsolutePath()+"src/main/resources/uploads";
            // set the properties value
            prop.setProperty("spring.jpa.hibernate.ddl-auto", "update");
            prop.setProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/db_example");
            prop.setProperty("spring.datasource.username", "root");
            prop.setProperty("spring.datasource.password", "root");

            prop.setProperty("spring.freemarker.expose-request-attributes", "true");

            prop.setProperty("upload.path", "/"+resourcesPath);


            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
