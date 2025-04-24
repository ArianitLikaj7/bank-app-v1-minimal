package org.example.common;

public class Message {
    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    public static String success(String msg) {
        return OK + "|" + msg;
    }

    public static String error(String msg) {
        return ERROR + "|" + msg;
    }
}