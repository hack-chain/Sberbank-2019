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
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository, OrderRepository orderRepository) {
        return args -> {
            HashMap<Long, Status> tmp = new HashMap<> ();
            tmp.put(Long.valueOf(4), Status.REJECTED);
            tmp.put(Long.valueOf(3), Status.ACCEPTED);
            orderRepository.save(new Order(Long.valueOf(3),"pizza", 1000, tmp));
            orderRepository.save(new Order(Long.valueOf(3), "inet", 1000, tmp));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
            ArrayList<Long> tmp1 = new ArrayList<Long>();
            tmp1.add(Long.valueOf(1));
            log.info("Preloading " + repository.save (new User("Dmitry", "+79154336070", "https")));
            log.info("Preloading " + repository.save(new User("Frodo Baggins", "88003543333","http",tmp1)));
        };
    }
}