package com.example.bottomnav;


import java.io.Serializable;

public class manageClientsModel implements Serializable {
    private String clientName,status;
    private int clientImg;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getClientImg() {
        return clientImg;
    }

    public void setClientImg(int clientImg) {
        this.clientImg = clientImg;
    }

    public manageClientsModel(String clientName,String status ,int clientImg) {
        this.clientName = clientName;
        this.clientImg = clientImg;
        this.status = status;
    }

}
