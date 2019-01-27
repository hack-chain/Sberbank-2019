package com.Sberbank.Sberbank.SMS;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "ACffe5c82b121bf1b625c9d071fdb8ef3b";
    public static final String AUTH_TOKEN =
            "431a06b5fd6e07eb8e38d8b28c3fe1c7";

    public static void main() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+79850937035"), // to
                        new PhoneNumber("+19083245918"), // from
                        "Для оплаты откройте Яндекс: https://yandex.ru/")
                .create();

        System.out.println(message.getSid());
    }
}

// SM93d6b5afe54c4f7980ced53c03880a76
// SMc7152c283d8848b0941ec6253df111c2
// SMcdf57feaa33e48b1bb4d265cae8925f0 +
// SMefc6459c0b014a77b1f23fddf21a74d3 +

