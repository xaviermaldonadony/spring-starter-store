package com.example.store;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
//@Scope("prototype")
public class OrderService {
    private PaymentService paymentService;

    @Autowired
    public OrderService(@Qualifier("stripe") PaymentService paymentService){
        this.paymentService = paymentService;
        System.out.println("Order service Created");
    }

    @PostConstruct
    public void init(){
        System.out.println("Order service PostConstruct");
    }

    @PreDestroy
    public void cleanUp(){
        System.out.println("Order service PreDestroy");
    }

    public void placeOrder(){
        paymentService.processPayment(10);
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

}

