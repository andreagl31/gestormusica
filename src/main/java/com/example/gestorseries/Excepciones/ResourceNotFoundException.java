package com.example.gestorseries.Excepciones;

public class ResourceNotFoundException extends RuntimeException{
        public ResourceNotFoundException(String message) {
            super(message);
    }
}
