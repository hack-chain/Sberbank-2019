package com.Sberbank.Sberbank.SMS;

import com.Sberbank.Sberbank.Orders.Order;
import com.Sberbank.Sberbank.Orders.Status;
import com.Sberbank.Sberbank.Users.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;

public class SMS {
    @Autowired
    JdbcTemplate jdbcTemplate;

    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "ACffe5c82b121bf1b625c9d071fdb8ef3b";
    public static final String AUTH_TOKEN =
            "431a06b5fd6e07eb8e38d8b28c3fe1c7";

    public static void main(String SMSText, Long userId) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message
                    .creator(new PhoneNumber("+" + userId), // to
                            new PhoneNumber("+19083245918"), // from
                            SMSText)
                    .create();

            System.out.println(message.getSid());
        } catch (Exception e) {

        }
    }

    private Order findById(Long id) {
        return jdbcTemplate.queryForObject("select * from orders where id=?", new Object[]{
                        id
                },
                new BeanPropertyRowMapper<Order>(Order.class));
    }

    public void sendVerification(Long userId, Long buyId, String hash) {
        main("Перейдите по ссылке для подтверждения оплаты: host//users/buyAccept/"+ buyId +"/" + userId + "/" + hash, userId);

        Order tmp = findById(buyId);
        tmp.getMap().replace(userId, Status.ACCEPTED);
    }
}
