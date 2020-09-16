package edu.csumb.flightapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Reservation {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id; //this is the reservation number
    @NonNull
    private String username;
    @NonNull
    private String flightNum;
    @NonNull
    private String ticketNum;
    @NonNull
    private double total;
    @NonNull
    private String departureInfo;


    public Reservation() {}

    @Ignore
    public Reservation(String username, String flightNum, String ticketNum, double total, String departureInfo) {
        this.username = username;
        this.flightNum = flightNum;
        this.ticketNum = ticketNum;
        this.departureInfo = departureInfo;
        this.total = total;
    }


    public int getId() {return id;}

    public void setId(int id){this.id = id;}

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @NonNull
    public String getDepartureInfo() {
        return departureInfo;
    }

    public void setDepartureInfo(@NonNull String departureInfo) {
        this.departureInfo = departureInfo;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", flightNum=" + flightNum +
                ", ticketNum=" + ticketNum +
                ", total=" + total +
                ", departureInfo='" + departureInfo + '\'' +
                '}';
    }
}
