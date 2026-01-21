package com.ambermount.warehouse.inventory_service.exception;
import com.ambermount.warehouse.inventory_service.api.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(NotFoundException.class)
   public ResponseEntity<ApiError> handleNotFound(NotFoundException ex, HttpServletRequest req) {
       return ResponseEntity.status(HttpStatus.NOT_FOUND)
               .body(new ApiError(404, "NOT_FOUND", ex.getMessage(), req.getRequestURI()));
   }
   @ExceptionHandler(BadRequestException.class)
   public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex, HttpServletRequest req) {
       return ResponseEntity.status(HttpStatus.BAD_REQUEST)
               .body(new ApiError(400, "BAD_REQUEST", ex.getMessage(), req.getRequestURI()));
   }
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
       String msg = ex.getBindingResult().getFieldErrors().stream()
               .findFirst()
               .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
               .orElse("Validation failed");
       return ResponseEntity.status(HttpStatus.BAD_REQUEST)
               .body(new ApiError(400, "VALIDATION_ERROR", msg, req.getRequestURI()));
   }
   @ExceptionHandler(Exception.class)
   public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest req) {
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
               .body(new ApiError(500, "INTERNAL_SERVER_ERROR", ex.getMessage(), req.getRequestURI()));
   }
}