package com.example.gdechetslabibup.service;

import com.example.gdechetslabibup.model.CollectionSchedule;
//import com.example.gdechetslabibup.model.RoleName;
import com.example.gdechetslabibup.model.User;
import com.example.gdechetslabibup.repos.CollectionScheduleRepository;
import com.example.gdechetslabibup.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationService {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionScheduleRepository collectionScheduleRepository;

    @Autowired
    private JavaMailSender mailSender;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);


    @Scheduled(cron = "0 0 8 * * ?")  // Tous les jours à 8h du matin
    public void sendDailyNotifications() {
        logger.debug("Lancement du processus de notification quotidienne.");

        LocalDate today = LocalDate.now();
        logger.debug("Date du jour: {}", today);

        List<CollectionSchedule> schedules = collectionScheduleRepository.findByDateDeCollect(today);
        logger.debug("Found " + schedules.size() + "  collection for today ");

        for (CollectionSchedule schedule : schedules) {
            User user = schedule.getUser();
            logger.debug("Sending email to: " + user.getEmail());

                sendNotification(user.getEmail(), schedule.getQuartier());
            
        }}
    

    private void sendNotification(String email, String quartier) {
        try {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Jour de collecte des déchets");
        message.setText("Bonjour, le jour de collecte des déchets pour votre quartier " + quartier + " est aujourd'hui.");
        mailSender.send(message);
        logger.debug("Email sent to " + email);
    } catch (Exception e) {
        logger.error("Failed to send email to " + email + ": " + e.getMessage());
    }
       
    }
}
