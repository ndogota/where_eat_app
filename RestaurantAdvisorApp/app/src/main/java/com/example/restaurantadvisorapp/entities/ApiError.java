package com.example.restaurantadvisorapp.entities;

import java.util.List;
import java.util.Map;

public class ApiError {
    private String message;
    private Map<String, List<String>> error;

    public String getMessage() {
        return message;
    }

    public Map<String, List<String>> getErrors() {
        return error;
    }
}
