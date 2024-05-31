package fr.univrouen.xmlProject.Exceptions.Errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
public class ValidationError {
    private Map<String, List<String>> fieldErrors;

    public ValidationError() {
        this.fieldErrors = new HashMap<>();
    }

    public void addFieldError(String fieldName, String errorMessage) {
        this.fieldErrors.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(errorMessage);
    }

    public static ResponseEntity<?> handleValidationErrors(BindingResult bindingResult) {
        ValidationError validationError = new ValidationError();

        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                validationError.addFieldError(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(validationError.getFieldErrors());
        }

        return null; // Pas d'errs de validation
    }
}