package com.example.webfb.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

@SpringBootApplication
public class Application {
    @Value("${upload.path}")
    private static String uploadPath;

    public static void main(String[] args){

        SpringApplication.run(Application.class, args);

        /*Properties prop = new Properties();
        OutputStream output = null;
        System.out.println(uploadPath);
        try {

            output = new FileOutputStream("src/main/resources/application.properties");//application-dev in localhost use

            String resourcesPath =  "home/pan/uploads"; //new File("").getAbsolutePath() +   application-dev in localhost use

            // set the properties value
            prop.setProperty("spring.jpa.hibernate.ddl-auto", "validate");
            prop.setProperty("spring.jpa.sql-show", "true");
            prop.setProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/db_example");
            prop.setProperty("spring.datasource.username", "root");
            prop.setProperty("spring.datasource.password", "root");

            prop.setProperty("spring.freemarker.expose-request-attributes", "true");

            prop.setProperty("upload.path", "/" + resourcesPath);

            prop.setProperty("spring.mail.host", "smtp.yandex.ru");
            prop.setProperty("spring.mail.username",  "webfbtest@yandex.ru");
            prop.setProperty("spring.mail.password", "Web2018test");
            prop.setProperty("spring.mail.port", "465");
            prop.setProperty("spring.mail.protocol",  "smtps");
            prop.setProperty("mail.debug",  "false");

            prop.setProperty("recaptcha.secret", "6LeYdWwUAAAAANuBPciIcxllBYtV_uaZcIM0sYXU");

            prop.setProperty("spring.session.jdbc.initialize-schema",  "always");
            prop.setProperty("spring.session.jdbc.table-name",  "SPRING_SESSION");

            prop.setProperty("hostname",  "192.168.1.7"); //application-dev in localhost use localhost:8080


            // save properties to project root folder
            prop.store(output, null);

        /*} catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }*/

    }
}
