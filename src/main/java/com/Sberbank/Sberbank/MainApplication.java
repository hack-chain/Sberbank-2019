package com.Sberbank.Sberbank;

import com.Sberbank.Sberbank.payments.QiwiPayer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		QiwiPayer payment = new QiwiPayer();
		System.out.println(payment.createPaymentForm("100", "0", "79850937035", "79991111111", "1836"));

		//https://qiwi.com/payment/form/99?extra%5B%27account%27%5D=%payee%&amountInteger=%sum_int%&amountFraction=%sum_fraction%&extra%5B%27comment%27%5D=%description%&currency=643&blocked[0]=account&blocked[1]=sum&blocked[2]=comment


		SpringApplication.run(MainApplication.class, args);
	}

}

