package com.Sberbank.Sberbank.Users;

public class UserNotFoundException extends RuntimeException  {
    UserNotFoundException(Long id) {
        super("Could not find user " + id);
    }
}
