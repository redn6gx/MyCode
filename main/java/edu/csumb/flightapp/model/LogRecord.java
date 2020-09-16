package edu.csumb.flightapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// TODO  annotate this class with @Entitry @PrimaryKey, @Ignore
//  Room should ignore the second constructor with 4 arguments

@Entity
public class LogRecord {

    public static final String TYPE_NEW_ACCOUNT ="new account";
    public static final String TYPE_RESERVATION="reservation";
    public static final String TYPE_CANCEL="cancel";


    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private long time;
    @NonNull
    private String transaction_type;   // new account, reservation, cancel reservation
    @NonNull
    private String username;
    @NonNull
    private String detailed_message;

    public LogRecord(){
    }

    @Ignore
    public LogRecord(java.util.Date datetime, String type, String username, String message){
        this.time = datetime.getTime();
        this.transaction_type = type;
        this.username = username;
        this.detailed_message = message;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public java.util.Date getDatetime() {
        return new java.util.Date(time);
    }

    public long getTime() { return time;}
    public void setTime(long time) { this.time = time; }

    @NonNull
    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(@NonNull String transaction_type) {
        this.transaction_type = transaction_type;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getDetailed_message() {
        return detailed_message;
    }

    public void setDetailed_message(@NonNull String detailed_message) {
        this.detailed_message = detailed_message;
    }

    @Override
    public String toString() {
        String s = getDatetime()+"  Transaction Type: "+transaction_type+"\nusername: "+username;
        if (transaction_type.equals(TYPE_NEW_ACCOUNT))  {
            return s;
        } else {
            return s + "\n" + detailed_message;
        }
    }

}
