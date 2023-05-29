package br.com.planilha.leitorapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LeitorPlanilhasAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeitorPlanilhasAppApplication.class, args);
    }

}
