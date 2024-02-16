//package com.harshproject;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.slf4j.Logger;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
//
//import com.harshproject.util.DepartmentEmailAspect;
//
//import static org.mockito.Mockito.*;
//
//class DepartmentEmailAspectTests {
//
//	@Mock
//	private MailSender mailSender;
//
//	@InjectMocks
//	private DepartmentEmailAspect departmentEmailAspect;
//
//	@BeforeEach
//	public void setUp() {
//	    MockitoAnnotations.openMocks(this);
//	}
//    @Mock
//    private Logger logger;
//    
//    DepartmentEmailAspect emailAspect = new DepartmentEmailAspect(mailSender);
//
//    @Test
//    void sendEmailForTask0() {
//        // Arrange
//
//        // Act
//        departmentEmailAspect.sendEmailForTask0();
//
//        // Assert
//        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
//    }
//
//    @Test
//    void sendEmailForTask1() {
//        // Arrange
//
//        // Act
//        departmentEmailAspect.sendEmailForTask1();
//
//        // Assert
//        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
//    }
//
//    @Test
//    void sendEmailForTask2() {
//        // Arrange
//
//        // Act
//        departmentEmailAspect.sendEmailForTask2();
//
//        // Assert
//        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
//    }
//
//    @Test
//    void sendEmailForTask3() {
//        // Arrange
//
//        // Act
//        departmentEmailAspect.sendEmailForTask3();
//
//        // Assert
//        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
//    }
//    
//
//    @Test
//    void handleHttpCodesWithResponseEntity() {
//        JoinPoint joinPoint = mock(JoinPoint.class);
//        ResponseEntity<String> result = ResponseEntity.status(HttpStatus.OK).body("OK");
//
//        emailAspect.handleHttpCodes(joinPoint, result);
//
//        verify(mailSender, never()).send(any(SimpleMailMessage.class));
//    }
//
//}
