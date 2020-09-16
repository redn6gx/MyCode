package edu.csumb.flightapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Dao;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.csumb.flightapp.model.FlightDao;
import edu.csumb.flightapp.model.FlightRoom;
import edu.csumb.flightapp.model.LogRecord;
import edu.csumb.flightapp.model.User;

public class CreateAccountActivity  extends AppCompatActivity {

    int errorCount = 0;
    int errorCount2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("CrateAccountActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount); //sets the layout to be used for this activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button create_button = findViewById(R.id.create_account_button); //finds id in activity_createaccount.xml
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.username);
                EditText password = findViewById(R.id.password);

                // TODO check that username and password meets requirements
                //   one special char, one uppercase and one lowercase letters, one digit

                if (username.getText().toString().equals("!admiM2")) {
                    // username already exists.
                    TextView msg = findViewById(R.id.message);
                    msg.setText("Username not available.");
                    msg.setTextColor(0xFFff0000); //this is green color
                    return;
                }

                if( !checkString(username.getText().toString()) || !checkString(password.getText().toString()) ){

                    TextView msg = findViewById(R.id.message);
                    msg.setText("Username or password does not meet requirements");
                    msg.setTextColor(0xFFff0000); //this is green color
                    errorCount++;

                    if(errorCount == 2){
                        //alert dialog
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(CreateAccountActivity.this);
                        builder1.setTitle("Too many failed account creations");
                        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        AlertDialog dialog = builder1.create();
                        dialog.show();
                    }
                        return;
                }

                //try to retrieve entered username from database and store in user variable
                User user = FlightRoom.getFlightRoom(CreateAccountActivity.this).
                        dao().getUserByName(username.getText().toString());

                if (user == null) {
                    // username does not exist, so add the new account
                    String name = username.getText().toString();
                    String pw = password.getText().toString();

                    //create a new user and add them to the database
                    User newUser = new User(name, pw);
                    FlightDao dao = FlightRoom.getFlightRoom(CreateAccountActivity.this).dao();
                    dao.addUser(newUser);

                    //TODO  write a record to Log table with message that new Account has been created.
                    //  include username (but not password) in the message.

                    Date now = new Date();
                    LogRecord rec = new LogRecord(now, LogRecord.TYPE_NEW_ACCOUNT, name, "");
                    dao.addLogRecord(rec);
                    
                    // inform user that new account has been created
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccountActivity.this);
                    builder.setTitle("Account successfully created.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {
                    // username already exists.
                    TextView msg = findViewById(R.id.message);
                    msg.setText("Username not available.");
                    msg.setTextColor(0xFFff0000); //this is green color
                    errorCount2++;

                    if(errorCount2 == 2){
                        //alert dialog
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(CreateAccountActivity.this);
                        builder1.setTitle("Too many failed account creations");
                        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        AlertDialog dialog = builder1.create();
                        dialog.show();
                    }
                    return;
                }

            }
        });
    }

    private static boolean checkString(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        boolean otherFlag = false;

        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }else{
                otherFlag = true;
            }
            if(numberFlag && capitalFlag && lowerCaseFlag && otherFlag)
                return true;
        }
        return false;
    }

}