package com.Sberbank.Sberbank;

import com.Sberbank.Sberbank.Payments.QiwiPayer;
import com.Sberbank.Sberbank.SMS.SMS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(MainApplication.class, args);
    }

}
