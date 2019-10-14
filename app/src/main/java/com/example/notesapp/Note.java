package com.example.notesapp;

import com.google.firebase.Timestamp;

public class Note {

    private String text;
    private boolean completed;
    private Timestamp created;
    private String userId;

    public Note() {
    }

    public Note(String text, boolean completed, Timestamp created, String userId) {
        this.text = text;
        this.completed = completed;
        this.created = created;
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Note{" +
                "text='" + text + '\'' +
                ", completed=" + completed +
                ", created=" + created +
                ", userId='" + userId + '\'' +
                '}';
    }
}
