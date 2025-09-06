package com.example.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationManager {

    private final NotificationService notifcationService;

    public NotificationManager(@Qualifier("email") NotificationService notifcationService){
        this.notifcationService = notifcationService;
    }

    public void sendNotification (String message){
        notifcationService.send(message);
    }
}
