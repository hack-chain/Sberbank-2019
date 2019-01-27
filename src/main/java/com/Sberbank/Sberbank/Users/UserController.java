package com.Sberbank.Sberbank.Users;


import java.util.List;
import java.util.stream.Collectors;

import com.Sberbank.Sberbank.Orders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private User findByPhoneNumber(String number) {
        return jdbcTemplate.queryForObject("select * from users where PHONE_NUMBER=?", new Object[]{
                        number
                },
                new BeanPropertyRowMapper<User>(User.class));
    }

    private final UserRepository repository;
    private final UserResourceAssembler userResourceAssembler;

    UserController(UserRepository repository, UserResourceAssembler assembler) {
        this.repository = repository;
        this.userResourceAssembler = assembler;
    }

    @GetMapping("/users")
    Resources<Resource<User>> all() {
        List<Resource<User>> users = repository.findAll().stream()
                .map(user -> new Resource<>(user,
                        linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).all()).withRel("users")))
                .collect(Collectors.toList());

        return new Resources<>(users,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    Resource<User> one(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userResourceAssembler.toResource(user);
    }

    @GetMapping("/users/phone/{number}")
    Resource<User> findByNumber(@PathVariable String number) {
        User user = findByPhoneNumber(number);
        return userResourceAssembler.toResource(user);
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/buyAccept/{buyId}/{userId}/{hash}")
    Resource<User> findToAccept(@PathVariable Long buyId, @PathVariable String number, @PathVariable String hash) {
        User user = findByPhoneNumber(number);
        // get hash from DB
        if (hash.equals("HASH_GOT_FROM_DB")) {
            return userResourceAssembler.toResource(user);
        } else {
            return null; // Что вернуть в случае несовпадения хеша?
        }
    }
}