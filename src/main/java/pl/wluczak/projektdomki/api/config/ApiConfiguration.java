package pl.wluczak.projektdomki.api.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.wluczak.projektdomki.api.dto.ErrorDto;
import pl.wluczak.projektdomki.exception.OperationNotAllowedException;


//Obsługa błędów
@ControllerAdvice
public class ApiConfiguration {

    @ExceptionHandler(value = OperationNotAllowedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDto handleOperationNotAllowedException(OperationNotAllowedException ex){
        return new ErrorDto(ex.getMessage());

    }
}
