package com.example.webfb.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.webfb.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class Application {

    public static void main(String[] args){

        SpringApplication.run(Application.class, args);

        FileInputStream  fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            property.load(fis);
            property.setProperty("upload.path", "E://buf//web");
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
