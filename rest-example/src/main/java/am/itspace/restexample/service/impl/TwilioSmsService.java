package am.itspace.restexample.service.impl;

import am.itspace.restexample.service.SmsService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsService implements SmsService {


    @Value("${twilio.accountSid}")
    private String accountSid;
    @Value("${twilio.accountSid}")
    private String authToken;

    @Override
    public String send(String phoneNumber, String message) {
        Twilio.init(accountSid, authToken);

        Message messageObj = Message.creator(new PhoneNumber(phoneNumber),
                new PhoneNumber("+37494324314"),
                message).create();

return messageObj.getSid();
    }
}
