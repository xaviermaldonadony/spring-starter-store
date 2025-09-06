package com.example.store;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final NotificationService notificationService;


    public UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void registerUser(User user){
        if (userRepository.findByEmail(user.getEmail()) != null){
           throw new IllegalArgumentException("User with email" + user.getEmail() + " already exist");
        }

        userRepository.save(user);
        notificationService.send("You registered succesfully:",  user.getEmail());
    }
}
