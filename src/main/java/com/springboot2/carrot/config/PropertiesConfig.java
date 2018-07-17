package com.springboot2.carrot.config;

import com.springboot2.carrot.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootConfiguration
public class PropertiesConfig {

    @Value("${hexin-name}")
    private String hexinName;

    @Bean
    public User user(){
        User user = new User();
        user.setName(hexinName);
        System.out.println("@Bean:"+user.getName());
        return user;
    }

    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct:"+this.toString());
    }

    @PreDestroy
    public void clean(){
        System.out.println("@PreDestroy:"+this.toString());
    }

    @Override
    public String toString() {
        return "PropertiesConfig{" +
                "hexinName='" + hexinName + '\'' +
                '}';
    }
}
