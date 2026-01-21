package com.ambermount.warehouse.catalog_service.exception;
import com.ambermount.warehouse.catalog_service.api.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.LinkedHashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
   Map<String, String> errors = new LinkedHashMap<>();
   for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
     errors.put(fe.getField(), fe.getDefaultMessage());
   }
   ApiError body = new ApiError(
       400,
       "Bad Request",
       "Validation failed",
       req.getRequestURI(),
       errors
   );
   return ResponseEntity.badRequest().body(body);
 }
 @ExceptionHandler(ErrorResponseException.class)
 public ResponseEntity<ApiError> handleSpringStatus(ErrorResponseException ex, HttpServletRequest req) {
   int status = ex.getStatusCode().value();
   ApiError body = new ApiError(
       status,
       ex.getStatusCode().toString(),
       ex.getMessage() != null ? ex.getMessage() : "Request failed",
       req.getRequestURI(),
       null
   );
   return ResponseEntity.status(status).body(body);
 }
 @ExceptionHandler(Exception.class)
 public ResponseEntity<ApiError> handleOther(Exception ex, HttpServletRequest req) {
   ApiError body = new ApiError(
       500,
       "Internal Server Error",
       "Unexpected error",
       req.getRequestURI(),
       null
   );
   return ResponseEntity.status(500).body(body);
 }
}