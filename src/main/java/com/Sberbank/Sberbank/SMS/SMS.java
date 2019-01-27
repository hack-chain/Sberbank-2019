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

    public static void main(String SMSText, String userId) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+" + userId), // to
                        new PhoneNumber("+19083245918"), // from
                        SMSText)
                .create();

        System.out.println(message.getSid());
    }

    public void sendVerification(String userId, String buyId, String hash) {
        main("Перейдите по ссылке для подтверждения оплаты: host//users/buyAccept/" + userId + "/" + hash, userId);
        // write hash+userId+buyId to DB
    }
}
