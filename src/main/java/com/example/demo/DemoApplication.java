package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.example.demo.servlet.SimpleEchoServlet;

@SpringBootApplication
public class DemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
    @Bean
    public ServletRegistrationBean<SimpleEchoServlet> simpleEchoServlet() {
        ServletRegistrationBean<SimpleEchoServlet> bean = new ServletRegistrationBean<>();
        bean.setServlet(new SimpleEchoServlet());
        bean.addUrlMappings("/simple-echo");
        return bean;
    }
}