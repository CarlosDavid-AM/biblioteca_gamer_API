package com.biblioteca.gemer.Exceptions;

public class JuegosExceptions extends RuntimeException {

    public String description;

    public JuegosExceptions(String message) {
        super(message);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
