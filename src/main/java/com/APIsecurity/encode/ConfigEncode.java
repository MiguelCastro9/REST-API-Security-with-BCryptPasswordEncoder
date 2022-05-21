package com.APIsecurity.encode;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Miguel Castro
 */
@Component
public class ConfigEncode {

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
