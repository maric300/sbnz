package com.ftn.sbnz.model.models;

import java.io.Serializable;

/**
 * Jednostavna klasa koja služi kao "zahtev" iz Drools pravila ka Java kodu.
 * Kada pravilo želi da pošalje notifikaciju, ono kreira i ubacuje
 * ovaj objekat u radnu memoriju.
 */
public class NotificationRequest implements Serializable {

    private String message;

    public NotificationRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}