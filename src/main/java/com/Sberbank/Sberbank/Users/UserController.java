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

    User findByPhoneNumber(String number) {
        return jdbcTemplate.queryForObject("select * from users where PHONE_NUMBER=?", new Object[] {
                        number
                },
                new BeanPropertyRowMapper<User>(User.class));
    }

    private final UserRepository repository;
    private final UserResourceAssembler userResourceAssembler;

    UserController(UserRepository repository,
                       UserResourceAssembler assembler,
                   OrderRepository orderRepository) {
        this.repository = repository;
        this.userResourceAssembler = assembler;
    }

    @GetMapping("/users")
    Resources<Resource<User>> all() {
        List<Resource<User>> users = repository.findAll().stream()
                .map(user -> new Resource<>(user,
                        linkTo(methodOn(UserController.class).one(user.getPhoneNumber())).withSelfRel(),
                        linkTo(methodOn(UserController.class).all()).withRel("users")))
                .collect(Collectors.toList());

        return new Resources<>(users,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    Resource<User> one(@PathVariable String id) {
        User user = findByPhoneNumber(id);
        return userResourceAssembler.toResource(user);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(User -> {
                    User.setName(newUser.getName());
                    return repository.save(User);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
