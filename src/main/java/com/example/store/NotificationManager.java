package com.example.store;

import org.springframework.stereotype.Component;

@Component
public class NotficationManager {

    NotificationService notifcationService;

    public NotificationManager(  NotificationService notifcationService){
        this.notifcationService = notifcationService;

    }
}
