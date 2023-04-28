package ru.practicum.exception;

public class ItemRetrieverException extends RuntimeException {
    private String message;
    private Exception exception;

    public ItemRetrieverException(String s) {
        this.message = s;
    }

    public ItemRetrieverException(String s, Exception exception) {
        this.message = s;
        this.exception = exception;
    }
}
