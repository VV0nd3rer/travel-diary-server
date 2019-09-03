package com.kverchi.diary.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Liudmyla Melnychuk on 3.9.2019.
 */
@Configuration
public class HibernateJacksonConfig {
    @Bean
    public Module hibernateModule(){
        return new Hibernate5Module();
    }
}
