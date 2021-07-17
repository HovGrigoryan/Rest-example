package am.itspace.restexample.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulingService {

//    @Scheduled(fixedDelay = 2000)
    @Scheduled(cron = "0 0 1 * * MON")
    public void test(){
        System.out.println("hello from test");
    }
}
