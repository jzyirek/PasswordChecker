package com.admin.passwordchecker;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener


{
    private Button returnDateButton;
    private String username;
    private String selectedDateString;
    private DatePicker datePicker;
    private SimpleDateFormat dateFormatter;
    private Date selectedDate;
    private String selectedTimeSave;
    private String nameToSave;
    private String emailToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        returnDateButton = (Button) findViewById(R.id.ReturnDateButtonId);
        datePicker = (DatePicker) findViewById(R.id.DatePickerId);

        returnDateButton.setOnClickListener(this);

        Intent intentG = getIntent();
        selectedTimeSave = intentG.getStringExtra("time");
        username = intentG.getStringExtra("username");
        nameToSave = intentG.getStringExtra("nameToSave");
        emailToSave = intentG.getStringExtra("emailToSave");


    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.ReturnDateButtonId)
        {
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();

//            selectedDate = new Date(year, month, day);
//            dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
//            selectedDateString = dateFormatter.format(selectedDate);
            selectedDateString = String.valueOf(month) + "-" + String.valueOf(day) + "-" + String.valueOf(year);

            Intent intent = new Intent(this, LoginSuccess.class);
            intent.putExtra("date",selectedDateString);
            intent.putExtra("time",selectedTimeSave);
            intent.putExtra("username",username);
            intent.putExtra("nameToSave",nameToSave);
            intent.putExtra("emailToSave",emailToSave);
            startActivity(intent);

        }

    }
}
