package com.example.gdechetslabibup.controller;

import com.example.gdechetslabibup.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@PreAuthorize("hasAnyRole('ADMIN','USER')")

public class TestController {

    @Autowired
    private NotificationService notificationService;
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")

    @GetMapping("/send-notifications")
    public String sendNotifications() {
        notificationService.sendDailyNotifications();
        return "Notifications envoyées.";
    }
}

