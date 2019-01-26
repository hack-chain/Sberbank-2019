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

import java.util.HashMap;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository, OrderRepository orderRepository) {
        return args -> {
            HashMap<Long, Status> tmp = new HashMap<> ();
            tmp.put(Long.valueOf(3), Status.CANCELLED);
            orderRepository.save(new Order(1000, tmp));
            orderRepository.save(new Order(2000, tmp));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
            log.info("Preloading " + repository.save(new User("Bilbo Baggins", "burglar")));
            log.info("Preloading " + repository.save(new User("Frodo Baggins", "thief")));
        };
    }
}