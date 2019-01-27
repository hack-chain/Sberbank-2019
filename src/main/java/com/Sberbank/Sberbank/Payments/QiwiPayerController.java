package com.Sberbank.Sberbank.Payments;

import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class QiwiPayerController {
    private final QiwiPayerRepository repository;


    QiwiPayerController(QiwiPayerRepository repository) {
        this.repository = repository;
    }

    /* Aggregate root */

//    @PostMapping("/payment")
//    public String homeInit(Model model) {
//        return "home";
//    }

//    QiwiPayer newQiwiPayer(@RequestBody QiwiPayer newQiwiPayer) {
//        return repository.save(newQiwiPayer);
//    }
}
