package com.example.hall_booking;

public class User {
    String HallName,Amount,Date,ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public User() {
    }

    public User(String hallName, String amount, String date,String ID) {
        HallName = hallName;
        Amount = amount;
        Date = date;
        ID = ID;
    }

    public String getHallName() {
        return HallName;
    }

    public void setHallName(String hallName) {
        HallName = hallName;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

}
