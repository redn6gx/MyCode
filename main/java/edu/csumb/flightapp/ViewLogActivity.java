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
import android.widget.TextView;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.LogRecord;
import edu.csumb.flightapp.model.User;

public class ViewLogActivity  extends AppCompatActivity {

    private ViewLogAdapter adapter;

    private List<LogRecord> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ViewLogActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button confirm_log_button = findViewById(R.id.confirm_log_button);
        confirm_log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewLogActivity.this, ManageSystemActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // retrieve all log records from database
        records = FlightRoom.getFlightRoom(this).dao().getAllLogRecords();

        if(records.size() == 0){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(ViewLogActivity.this);
            builder1.setTitle("No log records");
            builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    return;
                    Intent intent = new Intent(ViewLogActivity.this, ManageSystemActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            AlertDialog dialog2 = builder1.create();
            dialog2.show();
        }

        RecyclerView rv = findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ViewLogAdapter();
        rv.setAdapter(adapter);
    }
        private class ViewLogAdapter  extends RecyclerView.Adapter<ItemHolder> {
            @Override
            public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
                LayoutInflater layoutInflater = LayoutInflater.from(ViewLogActivity.this);
                return new ItemHolder(layoutInflater, parent);
            }
            @Override
            public void onBindViewHolder(ItemHolder holder, int position){
                holder.bind(records.get(position));
            }
            @Override
            public int getItemCount() { return records.size(); }
        }

        private class ItemHolder extends RecyclerView.ViewHolder {
            public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.item, parent, false));
            }
            public void bind(LogRecord rec) {
                TextView item = itemView.findViewById(R.id.item_id);
                item.setText(rec.toString());
            }
        }
}
