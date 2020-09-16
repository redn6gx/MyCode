package edu.csumb.flightapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import edu.csumb.flightapp.model.Flight;
import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.Reservation;
import edu.csumb.flightapp.model.User;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    public static String username = null;   // username if logged in
    public static Flight selectedFlight = null;
    public static int ticketsBought = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //sets the layout to be used for this activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // check database
        FlightRoom.getFlightRoom(MainActivity.this).loadData(this);

        Button create_account_button = findViewById(R.id.create_account); //finds id in activity_main.xml

        create_account_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // call the ShowUser Activity
                Log.d("MainActivity", "onClick for create account called");
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);

            }
        });

//        Button login_button = findViewById(R.id.login);
//
//        login_button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                // call the ShowUser Activity
//                Log.d("MainActivity", "onClick for login called");
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent);
//
//            }
//        });

//        Button user_button = findViewById(R.id.show_users);
//
//        user_button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                // call the ShowUser Activity
//                Log.d("MainActivity", "onClick for show users called");
//                Intent intent = new Intent(MainActivity.this, ShowUserActivity.class);
//                startActivity(intent);
//
//            }
//        });

//        Button flight_button = findViewById(R.id.show_flights);
//
//        flight_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // call the ShowFlight activity
//                Log.d("MainActivity", "onClick for show flights called");
//                Intent intent = new Intent(MainActivity.this, ShowFlightActivity.class);
//                startActivity(intent);
//            }
//        });


        Button sarch_flight_button = findViewById(R.id.search_flights);

        sarch_flight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call the ShowFlight activity
                Log.d("MainActivity", "onClick for search flights called");
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        //TODO add addition main buttons and controller methods for
        //  Cancel Reservation,
        //  Add Flight (administrator only),
        //  View Log (administrator only)
        //

        Button cancel_reservation_button = findViewById(R.id.cancel_reservation); //finds id in activity_main.xml

        cancel_reservation_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CancelReservationLoginActivity.class);
                startActivity(intent);
            }
        });

//        Button viewlog_button = findViewById(R.id.log);
//
//        viewlog_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // call the ShowFlight activity
//                Log.d("MainActivity", "onClick for view log called");
//                Intent intent = new Intent(MainActivity.this, ViewLogActivity.class);
//                startActivity(intent);
//            }
//        });

        Button manage_system_button = findViewById(R.id.manage_system);

        manage_system_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call the ShowFlight activity
                Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
