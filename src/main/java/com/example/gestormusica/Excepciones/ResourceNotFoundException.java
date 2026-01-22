package com.example.gestormusica.Excepciones;

public class ResourceNotFoundException extends RuntimeException{
        public ResourceNotFoundException(String message) {
            super(message);
    }
}
