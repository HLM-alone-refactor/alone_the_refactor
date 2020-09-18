package com.palehorsestudios.alone.gui.model;

public class PlayerStatus {
    private Status status;
    private String textOutput;

    public PlayerStatus(Status status, String textOutput) {
        this.status = status;
        this.textOutput = textOutput;
    }

    public Status getStatusUpdate() {
        return status;
    }

    public void setStatusUpdate(Status status) {
        this.status = status;
    }

    public String getTextOutput() {
        return textOutput;
    }

    public void setTextOutput(String textOutput) {
        this.textOutput = textOutput;
    }
}
