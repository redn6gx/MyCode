package edu.csumb.flightapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Date;

import edu.csumb.flightapp.model.Flight;
import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.LogRecord;
import edu.csumb.flightapp.model.Reservation;
import edu.csumb.flightapp.model.User;

public class ReservationConfirmationActivity extends AppCompatActivity {

    Flight temp = null;
    String tempUsername = null;
    String tempTickets = null;
    String departureInfo = null;
    double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LoginActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_confirmation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //this will retrieve the extra add to the intent
//        Intent intent = getIntent();
//        final String selectedFlight = intent.getStringExtra("selectedFlight");


        String message_text = "";

        temp = MainActivity.selectedFlight;
        tempUsername = MainActivity.username;
        tempTickets = MainActivity.ticketsBought+"";
        departureInfo = "Departure: "+temp.getDeparture()+"Arrival: "+temp.getArrival()+"Departure Time: "+temp.getDepartureTime();

        total = temp.getPrice() * MainActivity.ticketsBought;
        message_text += ("Account: " + MainActivity.username + "\n\nFlight Number: " + temp.getFlightNo() + "\nDeparture: " + temp.getDeparture() + "\nDestination: " + temp.getArrival()
                + "\nDeparture Time: " + temp.getDepartureTime() + "\nTickets: " + MainActivity.ticketsBought + "\nTotal: $" + total);

        TextView flightDisplayTextView = findViewById(R.id.flight_display);
        flightDisplayTextView.setText(message_text);

        Button confirm_button = findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create reservation object and add it to database
                //could create reservation object in search activity and add to database here
                Reservation confirmedReservation = new Reservation(tempUsername, temp.getFlightNo(), tempTickets, total, departureInfo);
                FlightRoom.getFlightRoom(ReservationConfirmationActivity.this).dao().addReservation(confirmedReservation);

                //update flight capacity in database for reserved flight
                int result = MainActivity.selectedFlight.getCapacity() - MainActivity.ticketsBought;
                MainActivity.selectedFlight.setCapacity(result);
                FlightRoom.getFlightRoom(ReservationConfirmationActivity.this).dao().updateFlight(MainActivity.selectedFlight);


                //log the reservation
                LogRecord record = new LogRecord();
                Date now = new Date();

                LogRecord rec = new LogRecord(now, LogRecord.TYPE_RESERVATION, MainActivity.username, confirmedReservation.toString());
                FlightRoom.getFlightRoom(ReservationConfirmationActivity.this).dao().addLogRecord(rec);

                finish();
            }
        });
    }
}