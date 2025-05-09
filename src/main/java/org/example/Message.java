package org.example;

/**
 * Utility class to generate success or error messages.
 */
public class Message {
    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    // Create success message
    public static String success(String msg) {
        return OK + "|" + msg;
    }

    // Create error message
    public static String error(String msg) {
        return ERROR + "|" + msg;
    }
}