package com.bridgelabz.book_store_app.exception;
import com.bridgelabz.book_store_app.DTO.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDTO> handlerNonExistingId(CustomException customException){
        ResponseDTO responseDTO = new ResponseDTO("Exception while parsing Rest Request", customException.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
