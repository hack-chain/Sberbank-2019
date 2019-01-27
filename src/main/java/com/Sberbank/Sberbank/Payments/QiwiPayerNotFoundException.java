package com.Sberbank.Sberbank.Payments;

public class QiwiPayerNotFoundException extends RuntimeException  {
    QiwiPayerNotFoundException(Long id) {
        super("Could not find user " + id);
    }
}
