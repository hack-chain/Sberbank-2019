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
        //SMS.main();
		//QiwiPayer payment = new QiwiPayer();
		//System.out.println(payment.createPaymentForm("100", "0", "79850937035", "79991111111", "1836"));
		//payment.checkPayment("1.5", "0", "79850937035", "79850937035", "test123 146", "1c9111a7bf73364806348fa580ddae8c");
//		System.out.println(payment.checkWithRegExp("{\"data\":[{\"txnId\":14773236556,\"personId\":79850937035,\"date\":\"2019-01-26T20:48:07+03:00\",\"errorCode\":0,\"error\":null,\"status\":\"SUCCESS\",\"type\":\"IN\",\"statusText\":\"Success\",\"trmTxnId\":\"3849540\",\"account\":\"Банк «Тинькофф Кредитные Системы»\",\"sum\":{\"amount\":10,\"currency\":643},\"commission\":{\"amount\":0,\"currency\":643},\"total\":{\"amount\":10,\"currency\":643},\"provider\":{\"id\":7,\"shortName\":\"QIWI Кошелек\",\"longName\":\"QIWI Кошелек\",\"logoUrl\":null,\"description\":null,\"keys\":\"мобильный кошелек, кошелек, перевести деньги, личный кабинет, отправить деньги, перевод между пользователями\",\"siteUrl\":null,\"extras\":[]},\"source\":{\"id\":99,\"shortName\":\"Перевод на QIWI Кошелек\",\"longName\":\"Доставляется мгновенно\",\"logoUrl\":\"https://static.qiwi.com/img/providers/logoBig/99_l.png\",\"description\":null,\"keys\":\"пополнить, перевести, qiwi, кошелек, оплатить, онлайн, оплата, счет, способ, услуга,перевести\",\"siteUrl\":\"http://www.qiwi.com\",\"extras\":[{\"key\":\"ceo_description\",\"value\":\"Пополнение QIWI Кошелька банковской картой без комисии, со счета мобильного телефона или через крупнейшую сеть QIWI Терминалов. Оплачивать услуги стало проще.\"},{\"key\":\"ceo_title\",\"value\":\"Пополнить QIWI Кошелек: с банковской карты, с баланса телефона, через QIWI Кошелек\"},{\"key\":\"is_spa_form_available\",\"value\":\"true\"}]},\"comment\":null,\"currencyRate\":1,\"paymentExtras\":[],\"features\":{\"chequeReady\":false,\"bankDocumentReady\":false,\"regularPaymentEnabled\":false,\"bankDocumentAvailable\":false,\"repeatPaymentEnabled\":false,\"favoritePaymentEnabled\":false,\"chatAvailable\":false,\"greetingCardAttached\":false},\"serviceExtras\":{},\"view\":{\"title\":\"QIWI Кошелек\",\"account\":\"Банк «Тинькофф Кредитные Системы»\"}}],\"nextTxnId\":null,\"nextTxnDate\":null}\n"));

        //		System.out.println(payment.checkWithRegExp("{\"data\":[{\"txnId\":14773236556,\"personId\":79850937035,\"date\":\"2019-01-26T20:48:07+03:00\",\"errorCode\":0,\"error\":null,\"status\":\"SUCCESS\",\"type\":\"IN\",\"statusText\":\"Success\",\"trmTxnId\":\"3849540\",\"account\":\"Банк «Тинькофф Кредитные Системы»\",\"sum\":{\"amount\":10,\"currency\":643},\"commission\":{\"amount\":0,\"currency\":643},\"total\":{\"amount\":10,\"currency\":643},\"provider\":{\"id\":7,\"shortName\":\"QIWI Кошелек\",\"longName\":\"QIWI Кошелек\",\"logoUrl\":null,\"description\":null,\"keys\":\"мобильный кошелек, кошелек, перевести деньги, личный кабинет, отправить деньги, перевод между пользователями\",\"siteUrl\":null,\"extras\":[]},\"source\":{\"id\":99,\"shortName\":\"Перевод на QIWI Кошелек\",\"longName\":\"Доставляется мгновенно\",\"logoUrl\":\"https://static.qiwi.com/img/providers/logoBig/99_l.png\",\"description\":null,\"keys\":\"пополнить, перевести, qiwi, кошелек, оплатить, онлайн, оплата, счет, способ, услуга,перевести\",\"siteUrl\":\"http://www.qiwi.com\",\"extras\":[{\"key\":\"ceo_description\",\"value\":\"Пополнение QIWI Кошелька банковской картой без комисии, со счета мобильного телефона или через крупнейшую сеть QIWI Терминалов. Оплачивать услуги стало проще.\"},{\"key\":\"ceo_title\",\"value\":\"Пополнить QIWI Кошелек: с банковской карты, с баланса телефона, через QIWI Кошелек\"},{\"key\":\"is_spa_form_available\",\"value\":\"true\"}]},\"comment\":null,\"currencyRate\":1,\"paymentExtras\":[],\"features\":{\"chequeReady\":false,\"bankDocumentReady\":false,\"regularPaymentEnabled\":false,\"bankDocumentAvailable\":false,\"repeatPaymentEnabled\":false,\"favoritePaymentEnabled\":false,\"chatAvailable\":false,\"greetingCardAttached\":false},\"serviceExtras\":{},\"view\":{\"title\":\"QIWI Кошелек\",\"account\":\"Банк «Тинькофф Кредитные Системы»\"}}],\"nextTxnId\":null,\"nextTxnDate\":null}\n"));

        //https://qiwi.com/payment/form/99?extra%5B%27account%27%5D=%payee%&amountInteger=%sum_int%&amountFraction=%sum_fraction%&extra%5B%27comment%27%5D=%description%&currency=643&blocked[0]=account&blocked[1]=sum&blocked[2]=comment


		SpringApplication.run(MainApplication.class, args);

//
//		new ScheduledThreadPoolExecutor(1, 1, 1000).invoke(new Forker("-1", payers, true));
//		payers.add("1");
//		payers.add("2");
        ArrayList<String> payers = new ArrayList<String>();
        ArrayList<String> usedPayers = new ArrayList<String>();
        int temp = 0;
        while (true) {
            for (String userId : payers) {
                if (usedPayers.contains(userId)) {
                    continue;
                }
                System.out.println(userId);
                if (QiwiPayer.checkPayment("1.5", "0", "79850937035", userId, "test123 146", "1c9111a7bf73364806348fa580ddae8c")) {
                    usedPayers.add(userId);
                    // and change DB
                }
            }
            if (temp == 1) {
                payers.add("1000");
            }
            if (temp == 2) {
                payers.add("79850937035");
            }
            temp++;
            TimeUnit.SECONDS.sleep(2);
        }
    }

}
