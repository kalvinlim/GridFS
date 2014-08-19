package com.main;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@ComponentScan("com")
public class Application {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        //inspectBeans(ctx);
    }
    
    public static void inspectBeans(ApplicationContext ctx){
        logger.info("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            logger.info(beanName);
        }
    } 
}

