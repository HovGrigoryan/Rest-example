package am.itspace.restexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DublicateEntityException extends RuntimeException {

    public DublicateEntityException() {
    }

    public DublicateEntityException(String message) {
        super(message);
    }
}
