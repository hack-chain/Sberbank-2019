package com.Sberbank.Sberbank.Payments;

import com.Sberbank.Sberbank.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QiwiPayerRepository extends JpaRepository<User, Long> {
}