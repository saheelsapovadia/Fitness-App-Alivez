package com.example.bottomnav;

import java.io.Serializable;

public class    TrainerBMIModel implements Serializable {
    private String clientName,bmi;
    private int clientImg;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getBMI() {
        return bmi;
    }

    public void setBMI(String bmi) {
        this.bmi = bmi;
    }

    public int getClientImg() {
        return clientImg;
    }

    public void setClientImg(int clientImg) {
        this.clientImg = clientImg;
    }

    public TrainerBMIModel(String clientName, String bmi, int clientImg) {
        this.clientName = clientName;
        this.bmi = bmi;
        this.clientImg = clientImg;
    }
}
