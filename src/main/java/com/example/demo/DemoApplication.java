package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    //객체의 생명주기를 관리하는 객체
    @Bean
    //serverendpoint 감지할 때 사용
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    };

}
