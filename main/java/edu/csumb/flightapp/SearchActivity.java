package edu.csumb.flightapp;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.csumb.flightapp.model.Flight;
import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.Reservation;

        public class SearchActivity extends AppCompatActivity {

            List<Flight> flights = new ArrayList<Flight>();
            Adapter adapter;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                Log.d("SearchActivity", "onCreate called");
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_search_flight);
                Toolbar toolbar = findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                Button return_main_button = findViewById(R.id.return_button);
                return_main_button.setOnClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Log.d("SearchActivity", "onClick return called");
                        finish();
                    }
                });

        Button search_button = findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("SearchActivity", "onClick search called");
                EditText from = findViewById(R.id.from_city);
                EditText to = findViewById(R.id.to_city);

                EditText tickets = findViewById(R.id.ticket_num);
                MainActivity.ticketsBought = Integer.parseInt(tickets.getText().toString());

                flights = FlightRoom.getFlightRoom(SearchActivity.this).dao().
                        searchFlight(from.getText().toString(),
                                     to.getText().toString());


                for(int i=0; i<flights.size(); i++){
                    if(flights.get(i).getCapacity() < MainActivity.ticketsBought ){
                        flights.remove(i);
                    }
                }


                if(flights.size() == 0){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(SearchActivity.this);
                    builder1.setTitle("No matching flights");
                    builder1.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    AlertDialog dialog = builder1.create();
                    dialog.show();
                }

                if(MainActivity.ticketsBought > 7){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(SearchActivity.this);
                    builder1.setTitle("Cannot exceed 7 tickets");
                    builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });

                    AlertDialog dialog = builder1.create();
                    dialog.show();
                }

                // notify recycler view that list of flights has changed
                adapter.notifyDataSetChanged();

            }
        });

        RecyclerView rv = findViewById(R.id.recycler_view);
        rv.setLayoutManager( new LinearLayoutManager(this));
        adapter = new Adapter();
        rv.setAdapter( adapter );
    }

    private class Adapter  extends RecyclerView.Adapter<ItemHolder> {

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(SearchActivity.this);
            return new  ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position){
            holder.bind(flights.get(position));
        }

        @Override
        public int getItemCount() { return flights.size(); }
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item, parent, false));
        }

        public void bind(Flight f ) {

            if (MainActivity.ticketsBought <= 7) {

                TextView item = itemView.findViewById(R.id.item_id);
                item.setText(f.toString());
                //make item clickable
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //save selected flight into the public static variable in MainActivity
                        MainActivity.selectedFlight = flights.get(getAdapterPosition());

                        //switch to new activity
                        Intent intent = new Intent(SearchActivity.this, ReservationLoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

            }
        }
    }
}
