package edu.csumb.flightapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.csumb.flightapp.model.Flight;
import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.LogRecord;

public class ManageSystemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        finish();
                }
            }
        };

        AlertDialog.Builder builder1 = new AlertDialog.Builder(ManageSystemActivity.this);
        builder1.setMessage("Would you like to add a flight?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

        Button enter_button = findViewById(R.id.enter_button);
        enter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText flightNo = findViewById(R.id.flight_number);
                EditText departure = findViewById(R.id.departure);
                EditText arrival = findViewById(R.id.arrival);
                EditText departureTime = findViewById(R.id.departure_time);
                EditText capacity = findViewById(R.id.capacity);
                EditText price = findViewById(R.id.price);

                //check if all fields have been filled
                if (flightNo.getText().toString().equals("") || departure.getText().toString().equals("") ||
                        arrival.getText().toString().equals("") || departureTime.getText().toString().equals("")
                        || capacity.getText().toString().equals("") || price.getText().toString().equals("")) {

                    AlertDialog.Builder builder2 = new AlertDialog.Builder(ManageSystemActivity.this);
                    builder2.setTitle("Some fields are missing data");
                    builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    AlertDialog dialog2 = builder2.create();
                    dialog2.show();
                } else {
                    //create the flight
                    final Flight tempFlight = new Flight(flightNo.getText().toString(), departure.getText().toString(),
                            arrival.getText().toString(), departureTime.getText().toString(),
                            Integer.parseInt(capacity.getText().toString()),
                            Double.parseDouble(price.getText().toString()));

                    //check to see if the flight is already in database or not
                    List<Flight> flights = FlightRoom.getFlightRoom(ManageSystemActivity.this).dao().findMatchingFlight(tempFlight.getFlightNo());

                    if (flights.size() != 0) {
                        AlertDialog.Builder builder3 = new AlertDialog.Builder(ManageSystemActivity.this);
                        builder3.setTitle("This flight already exists");
                        builder3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        AlertDialog dialog3 = builder3.create();
                        dialog3.show();
                    } else {

                        //if all info is valid then display flight info and prompt admin to see if info is correct
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        //Yes button clicked
                                        FlightRoom.getFlightRoom(ManageSystemActivity.this).dao().
                                                addFlight(tempFlight);
                                        finish();
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        //No button clicked
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder4 = new AlertDialog.Builder(ManageSystemActivity.this);
                        builder4.setMessage("Is this information correct?").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();
                    }
                }
            }
        });
    }
}
