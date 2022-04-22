package com.example.letschatapp;

import com.google.firebase.database.ServerValue;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Map;

public class Message {
    private String sender;
    private String receiver;
    private String time;

    public Message(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
        DateTime dt = new DateTime();
        time = dt.toString();
    }

    public Message() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
