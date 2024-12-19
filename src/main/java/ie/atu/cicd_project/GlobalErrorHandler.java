package ie.atu.cicd_project;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> showErrors(MethodArgumentNotValidException ex)
    {
        Map<String, String> errorList = new HashMap<>();
        for(FieldError error : ex.getFieldErrors()) {
            String err_name = error.getField();
            String err_message = error.getDefaultMessage();
            errorList.put(err_name, err_message);
        }
        return ResponseEntity.status(406).body(errorList);
    }
    //for not entering mapped url
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> urlNotHere()
    {
        return ResponseEntity.status(406).body("Enter valid url");
    }
    //for entering wrong format type ie /nathan/string instead of /nathan/number
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> parameterNotMatching()
    {
        return ResponseEntity.status(406).body("Wrong type entered");
    }
}
