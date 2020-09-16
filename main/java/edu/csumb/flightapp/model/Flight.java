package edu.csumb.flightapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Flight {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    private String flightNo;
    @NonNull
    private String departure;
    @NonNull
    private String arrival;
    @NonNull
    private String departureTime;
    @NonNull
    private int capacity;
    @NonNull
    private double price;
    @NonNull
    private int availableSeats;

    public Flight() {}

    @Ignore
    public Flight(String flightNo, String departure, String arrival, String departureTime, int capacity, double price) {
        this.flightNo = flightNo;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.capacity = capacity;
        this.price = price;
        this.availableSeats = capacity;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(@NonNull String flightNo) {
        this.flightNo = flightNo;
    }

    @NonNull
    public String getDeparture() {
        return departure;
    }

    public void setDeparture(@NonNull String departure) {
        this.departure = departure;
    }

    @NonNull
    public String getArrival() {
        return arrival;
    }

    public void setArrival(@NonNull String arrival) {
        this.arrival = arrival;
    }

    @NonNull
    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(@NonNull String departureTime) {
        this.departureTime = departureTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "id:  "+id+" flightNo:  "+flightNo+"\nfrom:  "+departure+" to:  "
                +arrival+"\ndeparture time:  "+departureTime+ " flight capacity: "+capacity
                +"\nprice: "+price;
    }

}
