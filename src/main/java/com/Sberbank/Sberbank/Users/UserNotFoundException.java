package com.Sberbank.Sberbank.Users;

class UserNotFoundException extends RuntimeException  {
    UserNotFoundException(Long id) {
        super("Could not find user " + id);
    }
}
