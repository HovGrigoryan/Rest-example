package am.itspace.restexample.service.impl;

import am.itspace.restexample.service.SmsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SMSCSmsServiceTest {


    private SmsService smsService = Mockito.mock(SMSCSmsService.class);


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void send() {
        Mockito.when(smsService.send("+37494324314","hello")).thenReturn("12345678");

        String hello = smsService.send("+37494324314", "hello");
        assertNotNull(hello);
    }
}