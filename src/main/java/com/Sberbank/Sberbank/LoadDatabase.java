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

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository, OrderRepository orderRepository) {
        return args -> {
            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
            log.info("Preloading " + repository.save(new User("Bilbo Baggins", "burglar")));
            log.info("Preloading " + repository.save(new User("Frodo Baggins", "thief")));
        };
    }
}