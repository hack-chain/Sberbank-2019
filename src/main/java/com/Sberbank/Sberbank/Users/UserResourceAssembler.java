package com.Sberbank.Sberbank.Users;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {

    @Override
    public Resource<User> toResource(User employee) {

        return new Resource<>(employee,
                linkTo(methodOn(UserController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("employees"));
    }
}