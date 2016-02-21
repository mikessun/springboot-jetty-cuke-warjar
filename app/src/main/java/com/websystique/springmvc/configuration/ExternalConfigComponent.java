package com.websystique.springmvc.configuration;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@Configuration
public class ExternalConfigComponent {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {

        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        List<Resource> list = new ArrayList<Resource>();
        list.add(new ClassPathResource("app.properties"));
        String property = System.getProperty("config.location");
        if (property != null && new File(property).exists()) {
            list.add(new FileSystemResource(property));
        }

        propertySourcesPlaceholderConfigurer.setLocations( list.toArray(new Resource[list.size()]));
        return propertySourcesPlaceholderConfigurer;
    }

    private static Logger logger = LoggerFactory.getLogger(ExternalConfigComponent.class);

    @Value("${val1}")
    public String propertyOne;

    @Value("${val2}")
    public String propertyTwo;

    @Value("${val3}")
    public String propertyThree;

    @PostConstruct
    public void postConstruct() {
        logger.info("Property One: " + propertyOne);
        logger.info("Property Two: " + propertyTwo);
        logger.info("Property Three: " + propertyThree);
    }

}