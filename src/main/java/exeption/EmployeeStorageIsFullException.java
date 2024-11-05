package exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
public class EmployeeStorageIsFullException extends RuntimeException{

    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}
