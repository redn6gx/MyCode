package edu.csumb.flightapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.csumb.flightapp.model.Flight;
import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.LogRecord;
import edu.csumb.flightapp.model.Reservation;

public class CancelReservationActivity extends AppCompatActivity{

    List<Reservation> userReservations = new ArrayList<Reservation>();
    Adapter adapter;
    Reservation selectedReservation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CancelReservation", "onCreate called");
        setContentView(R.layout.activity_cancel_reservation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = findViewById(R.id.reservation_recycler_view);
        rv.setLayoutManager( new LinearLayoutManager(this));
        adapter = new Adapter();
        rv.setAdapter( adapter );

        userReservations = FlightRoom.getFlightRoom(CancelReservationActivity.this).dao().
                getUserReservations(MainActivity.username);

        if(userReservations.size() == 0){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(CancelReservationActivity.this);
            builder1.setTitle("You have no reservations");
            builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog dialog = builder1.create();
            dialog.show();
        }

        // notify recycler view that list of reservations has changed
        adapter.notifyDataSetChanged();
    }

    private class Adapter  extends RecyclerView.Adapter<ItemHolder> {

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(CancelReservationActivity.this);
            return new ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position){
            holder.bind(userReservations.get(position));
        }

        @Override
        public int getItemCount() { return userReservations.size(); }
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item, parent, false));
        }

        public void bind(Reservation r ) {
            TextView item = itemView.findViewById(R.id.item_id);
            item.setText(r.toString());
            //make item clickable
            itemView.setOnClickListener(  new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //save selected flight into the public static variable in MainActivity
                    selectedReservation = userReservations.get(getAdapterPosition());

                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    //Yes button clicked
                                    FlightRoom.getFlightRoom(CancelReservationActivity.this).dao().deleteReservation(selectedReservation);

                                    Date now = new Date();
                                    LogRecord rec = new LogRecord(now, LogRecord.TYPE_CANCEL, MainActivity.username, selectedReservation.toString());
                                    FlightRoom.getFlightRoom(CancelReservationActivity.this).dao().addLogRecord(rec);
                                    finish();
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(CancelReservationActivity.this);
                                    builder1.setTitle("Cancellation Failed");
                                    builder1.setPositiveButton("Return Home", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    });

                                    AlertDialog dialog2 = builder1.create();
                                    dialog2.show();
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(CancelReservationActivity.this);
                    builder.setMessage("Do you really want to cancel the reservation?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();


                }
            });

        }
    }
}
