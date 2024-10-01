package com.restaurantApp.restaurantBack.globalExeptionHandler;


import com.restaurantApp.restaurantBack.exception.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice

public class GlobalExceptionHandler {

    ProblemDetail errorDetail = null;



    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException exception){
        String errorMessage = "Invalid parameter : "+ exception.getName()+" should be a valid Integer.";
        return new  ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleMessageNotReadable(HttpMessageNotReadableException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Malformed JSON Request");
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> handleConstraintViolationException(ConstraintViolationException ex){

        Map<String,String> errors = new HashMap<>();

        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        for(ConstraintViolation<?> violation : violations){
            String fieldName = violation.getPropertyPath().toString();
            if(!fieldName.isEmpty()){
                String errorMessage = violation.getMessage();
                errors.put(fieldName,errorMessage);
            }
        }
        return new  ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleItemNotFound(ItemNotFoundException exception){
        String errorMessage = exception.getMessage();
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFound(CustomerNotFoundException exception){
        String errorMessage = exception.getMessage();
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyCardException.class)
    public ResponseEntity<String> handleEmptyCart(EmptyCardException exception){
        String errorMessage = exception.getMessage();
        return new ResponseEntity<>(errorMessage,HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<String> handleCartItemNotFound(CartItemNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TableNotFoundException.class)
    public ResponseEntity<String> handleTableNotFound(TableNotFoundException exception){
        String errorMessage = exception.getMessage();
        return new ResponseEntity<>(errorMessage,HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MaxActiveReservationException.class)
    public ResponseEntity<String> handleMaxActiveReservation(MaxActiveReservationException exception){
        String errorMessage = exception.getMessage();
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TableIsReservedException.class)
    public ResponseEntity<String> handleTableReservedException(TableIsReservedException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoTableAvailableException.class)
    public ResponseEntity<String> handleNoTableAvailable(NoTableAvailableException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(NoReservationsFoundException.class)
    public ResponseEntity<String> handleNoReservationFound(NoReservationsFoundException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(ReservationModificationNotAllowedException.class)
    public ResponseEntity<String> handleReservationModificationNotAllowed(ReservationModificationNotAllowedException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SeatingCapacityExceededException.class)
    public ResponseEntity<String> handleSeatingCapacityExceeded(SeatingCapacityExceededException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String> handleReservationNotFound(ReservationNotFoundException exception){

        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<String> handleCartNotFound(CartNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<String> handleMenuItemNotFound(MenuItemNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(BadCredentialsException exception){
        errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
        errorDetail.setProperty("description", "The username or password is incorrect");
        return new ResponseEntity<>(errorDetail.getDetail(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<String> handleAccountStatusException(AccountStatusException exception){
        errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
        errorDetail.setProperty("description", "The account is locked");
        return new ResponseEntity<>(errorDetail.getDetail(),HttpStatus.BAD_REQUEST);

    }

}
