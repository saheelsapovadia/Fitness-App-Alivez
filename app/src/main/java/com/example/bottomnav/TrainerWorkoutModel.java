package com.example.bottomnav;

import java.io.Serializable;

public class TrainerWorkoutModel implements Serializable {
    private String clientName,routine;
    private int clientImg;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getRoutine() {
        return routine;
    }

    public void setRoutine(String routine) {
        this.routine = routine;
    }

    public int getClientImg() {
        return clientImg;
    }

    public void setClientImg(int clientImg) {
        this.clientImg = clientImg;
    }

    public TrainerWorkoutModel(String clientName, String routine, int clientImg) {
        this.clientName = clientName;
        this.routine = routine;
        this.clientImg = clientImg;
    }

}
