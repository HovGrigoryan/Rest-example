package am.itspace.restexample.service;

import java.util.List;

public interface SmsService {

    String send(String phoneNumber,String message);
}
