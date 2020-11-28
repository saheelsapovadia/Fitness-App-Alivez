package com.example.bottomnav;

public class userDiet {
    String title;
    String slot_time;
    String slot_date;
    public userDiet(){

    };

    public userDiet(String title, String slot_time, String slot_date) {
        this.title = title;
        this.slot_time = slot_time;
        this.slot_date = slot_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlot_time() {
        return slot_time;
    }

    public void setSlot_time(String slot_time) {
        this.slot_time = slot_time;
    }

    public String getSlot_date() {
        return slot_date;
    }

    public void setSlot_date(String slot_date) {
        this.slot_date = slot_date;
    }
}
