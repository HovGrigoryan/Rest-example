package am.itspace.restexample.service.impl;

import am.itspace.restexample.service.SmsService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SMSCSmsService implements SmsService {


    private String login = "grigoryanhov1";
    private String password = "FZJFW2Qsy7F3Tf8";

    @Override
    public String send(String phoneNumber, String message) {
        String url =String.format("https://smsc.ru/sys/send.php?login=%s&psw=%s&phones=%s&mes=%s",login,password,phoneNumber,message) ;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);

    }
}
