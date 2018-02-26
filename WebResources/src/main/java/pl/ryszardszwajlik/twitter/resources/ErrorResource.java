package pl.ryszardszwajlik.twitter.resources;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.ryszardszwajlik.twitter.exceptions.UserNotFoundException;

@ControllerAdvice
public class ErrorResource extends ResponseEntityExceptionHandler
{
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleBadRequestErrors(Exception ex)
    {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
