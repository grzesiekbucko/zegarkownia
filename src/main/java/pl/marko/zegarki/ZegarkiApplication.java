package pl.marko.zegarki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
public class ZegarkiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ZegarkiApplication.class, args);


    }

}