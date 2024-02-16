//package com.harshproject;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.harshproject.entity.UserInfo;
//import com.harshproject.repository.UserInfoRepository;
//import com.harshproject.service.AuthService;
//
//@SpringBootTest
//class AuthServiceTest {
//
//    @Mock
//    private UserInfoRepository repository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private AuthService authService;
//
//    @Test
//    void testAddUser() {
//
//        UserInfo userInfo = new UserInfo();
//        userInfo.setName("testUser");
//        userInfo.setPassword("testPassword");
//
//        String encodedPassword = "encodedTestPassword";
//
//        Mockito.when(passwordEncoder.encode(userInfo.getPassword())).thenReturn(encodedPassword);
//
//        Mockito.when(repository.save(userInfo)).thenReturn(userInfo);
//
//        String result = authService.addUser(userInfo);
//
//        assertEquals("User added to system.", result);
//
//        Mockito.verify(passwordEncoder).encode("testPassword");
//
//        Mockito.verify(repository).save(userInfo);
//    }
//
//}
