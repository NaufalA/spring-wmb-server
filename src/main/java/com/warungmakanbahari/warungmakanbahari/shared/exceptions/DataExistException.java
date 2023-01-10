package com.warungmakanbahari.warungmakanbahari.shared.exceptions;

public class DataExistException extends RuntimeException {
    public DataExistException() {
        super("Data Already Exist");

    }
    public DataExistException(String message) {
        super(message);
    }
}
