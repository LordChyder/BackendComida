package com.coderdot.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;

public class OperationResult {
    private Boolean success;
    private LocalDateTime timestamp;
    private List<String> message;

    public OperationResult(Boolean success, List<String> message) {
        this.success = success;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    
    @SuppressWarnings("rawtypes")
    public static ResponseEntity getOperationResult(Boolean success, List<String> messages) {
        OperationResult operationResult = new OperationResult(success, messages);

        if (success) {
            if (messages.size() == 0) {
                return ResponseEntity.ok(operationResult);
            } else {
                return ResponseEntity.ok(operationResult);
            }
        } else {
            if (messages.size() == 0) {
                return ResponseEntity.ok(operationResult);
            } else {
                return ResponseEntity.badRequest().body(operationResult);
            }
        }
    }


    public Boolean getSuccess() {
        return success;
    }
    
    public List<String> getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}