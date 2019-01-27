package com.Sberbank.Sberbank;

import com.Sberbank.Sberbank.Orders.OrderRepository;
import com.Sberbank.Sberbank.Users.User;
import com.Sberbank.Sberbank.Orders.Order;
import com.Sberbank.Sberbank.Orders.Status;
import com.Sberbank.Sberbank.Users.UserRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;

@Configuration
@Slf4j
class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, OrderRepository orderRepository) {
        return args -> {
            HashMap<Long, Status> tmp = new HashMap<> ();
            tmp.put(Long.valueOf(4), Status.NOT_PAID);
            tmp.put(Long.valueOf(3), Status.PAID);
            orderRepository.save(new Order(Long.valueOf(3),"pizza", 1000, tmp));
            orderRepository.save(new Order(Long.valueOf(3), "inet", 1000, tmp));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
            ArrayList<Long> tmp1 = new ArrayList<Long>();
            tmp1.add(Long.valueOf(1));
            log.info("Preloading " + userRepository.save (new User("Дмитрий Инютин", "+79154336070", "https://avatars0.githubusercontent.com/u/28871177?s=88&v=4")));
            log.info("Preloading " + userRepository.save(new User("Бильбо Бэггинс", "88003543333","https://pbs.twimg.com/profile_images/592321924220358656/gTw991-y_400x400.jpg",tmp1)));
        };
    }
}