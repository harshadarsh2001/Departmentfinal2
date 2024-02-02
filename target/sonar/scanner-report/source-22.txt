package com.harshproject.scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerComponent {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerComponent.class);

    @Scheduled(cron = "*/10 * * * * *")
    public void sendScheduledTaskConfirmationEmailEvery10sec() {
        logger.info("Displaying message every 10 sec: Scheduled Task 0");
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void sendScheduledTaskConfirmationEmailEvery5Seconds() {
        logger.info("Displaying message every 5 minutes: Scheduled Task 1");
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void sendScheduledTaskConfirmationEmailEvery30Minutes() {
        logger.info("Displaying message every 30 minutes: Scheduled Task 2");

    }

    @Scheduled(cron = "0 0 */1 * * *")
    public void sendScheduledTaskConfirmationEmailEvery1hr() {
        logger.info("Displaying message every 1 hr: Scheduled Task 3");

    }
}
