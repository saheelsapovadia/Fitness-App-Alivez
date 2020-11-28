package com.example.bottomnav;

public class TrainerProfileModel {
    private String clientname,status;
    private int clientimg;

    public TrainerProfileModel(String clientname, String status, int clientimg) {
        this.clientname = clientname;
        this.status = status;
        this.clientimg = clientimg;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getClientimg() {
        return clientimg;
    }

    public void setClientimg(int clientimg) {
        this.clientimg = clientimg;
    }
}
