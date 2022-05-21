package com.APIsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 *
 * @author Miguel Castro
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ApIsecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApIsecurityApplication.class, args);
    }

}
