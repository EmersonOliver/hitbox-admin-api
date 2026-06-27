package br.com.hitbox.infra.exception;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.interfaces.error.ApiError;
import br.com.hitbox.interfaces.error.BusinessErrorApi;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class HitboxExceptionHandler {

    @ExceptionHandler(HitboxException.class)
    public ResponseEntity<ApiError> handlerHitboxException(
            HitboxException ex,
            HttpServletRequest request) {

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        log.error("HitboxExceptionHandler.handlerHitboxException--> MessageError {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handlerException(
            Exception ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof AuthorizationDeniedException) {
            status = HttpStatus.BAD_REQUEST;
        }
        ApiError error = new ApiError(
                status.value(),
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        log.error("HitboxExceptionHandler.handlerException--> MessageError {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(status)
                .body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> dataIntegrityViolationExceptionHandler(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {

        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        log.error("HitboxExceptionHandler.dataIntegrityViolationExceptionHandler--> MessageError {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    @ExceptionHandler(HitboxBusinessException.class)
    public ResponseEntity<BusinessErrorApi<Long, Object>> hitboxBusinessException(
            HitboxBusinessException ex,
            HttpServletRequest request) {
        Long id = 0L;
        if (ex.getType().equals(Inventory.class)) {
            var inventory = (Inventory) ex.getEntity();
            id = inventory.getId();
        }

        BusinessErrorApi<Long, Object> error = new BusinessErrorApi<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now(),
                id,
                ex.getEntity()
        );

        log.error("HitboxExceptionHandler.dataIntegrityViolationExceptionHandler--> MessageError {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
