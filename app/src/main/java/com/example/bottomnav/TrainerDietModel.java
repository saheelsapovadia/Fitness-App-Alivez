package com.example.bottomnav;

import java.io.Serializable;

public class TrainerDietModel implements Serializable {
    private String clientName,chart;
    private int clientImg;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getChart() {
        return chart;
    }

    public void setChart(String chart) {
        this.chart = chart;
    }

    public int getClientImg() {
        return clientImg;
    }

    public void setClientImg(int clientImg) {
        this.clientImg = clientImg;
    }

    public TrainerDietModel(String clientName, String chart, int clientImg) {
        this.clientName = clientName;
        this.chart = chart;
        this.clientImg = clientImg;
    }
}
