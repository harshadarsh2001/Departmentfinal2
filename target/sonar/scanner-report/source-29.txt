package com.harshproject.util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DepartmentEmailAspect {

	private final MailSender mailSender;
    public DepartmentEmailAspect(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    private static final Logger logger = LoggerFactory.getLogger(DepartmentEmailAspect.class);

    // Define a constant for the email address
    private static final String FAKE_EMAIL = "fake-email@example.com";

    @Before("execution(* com.harshproject.scheduler.SchedulerComponent.sendScheduledTaskConfirmationEmailEvery10sec())")
    public void sendEmailForTask0() {
        sendEmail(FAKE_EMAIL, "Scheduled Task Confirmation 0",
                "The scheduled task has been executed successfully: Task Executed Every 10 sec");
    }

    @Before("execution(* com.harshproject.scheduler.SchedulerComponent.sendScheduledTaskConfirmationEmailEvery5Seconds())")
    public void sendEmailForTask1() {
        sendEmail(FAKE_EMAIL, "Scheduled Task Confirmation 1",
                "The scheduled task has been executed successfully: Task Executed Every 5 min");
    }

    @Before("execution(* com.harshproject.scheduler.SchedulerComponent.sendScheduledTaskConfirmationEmailEvery30Minutes())")
    public void sendEmailForTask2() {
        sendEmail(FAKE_EMAIL, "Scheduled Task Confirmation 2",
                "The scheduled task has been executed successfully: Task Executed Every 30 Minutes");
    }

    @Before("execution(* com.harshproject.scheduler.SchedulerComponent.sendScheduledTaskConfirmationEmailEvery1hr())")
    public void sendEmailForTask3() {
        sendEmail(FAKE_EMAIL, "Scheduled Task Confirmation 3",
                "The scheduled task has been executed successfully: Task Executed Every 1 hour");
    }

    @SuppressWarnings("deprecation")
    @AfterReturning(pointcut = "execution(* com.harshproject.controller.DepartmentController.*(..))", returning = "result")
    public void handleHttpCodes(JoinPoint joinPoint, Object result) {
        String methodName = "";
        if (joinPoint.getSignature() != null) {
            methodName = joinPoint.getSignature().getName();
        }
        logger.info("Handling HTTP codes for method: {}", methodName);

        if (result instanceof ResponseEntity) {
            int statusCodeValue = ((ResponseEntity<?>) result).getStatusCodeValue();

            // Add your custom logic based on HTTP status codes
            switch (statusCodeValue) {
                case 403:
                    logger.info("HTTP 403 Not Found");
                    sendErrorEmail(methodName, "HTTP 403 Not Found");
                    break;
                case 204:
                    logger.info("HTTP 204 OK");
                    sendConfirmationEmail(methodName, "HTTP 204 OK");
                    break;

                case 200:
                    logger.info("HTTP 200 OK");
                    sendConfirmationEmail(methodName, "HTTP 200 OK");
                    break;
                case 201:
                    logger.info("HTTP 201 Created");
                    sendConfirmationEmail(methodName, "HTTP 201 Created");
                    break;
                case 404:
                    logger.info("HTTP 404 Not Found");
                    sendErrorEmail(methodName, "HTTP 404 Not Found");
                    break;

                default:
                    logger.info("Unhandled HTTP status code: {}", statusCodeValue);
                    sendErrorEmail(methodName, "Unhandled HTTP status code: " + statusCodeValue);
            }
        } else {
            logger.warn("Unexpected result type: {}", result.getClass().getName());
            sendErrorEmail(methodName, "Unexpected result type: " + result.getClass().getName());
        }
    }

    private void sendEmail(String to, String subject, String text) {
        if (mailSender != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        } else {
            logger.error("MailSender is null. Email not sent.");
        }
    }

    public void sendConfirmationEmail(String methodName, String message) {
        sendEmail(FAKE_EMAIL, "Method Execution Confirmation - " + methodName, message);
    }

    public void sendErrorEmail(String methodName, String errorMessage) {
        sendEmail(FAKE_EMAIL, "Error in Method Execution - " + methodName, errorMessage);
    }
}


