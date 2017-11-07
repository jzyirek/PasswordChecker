package com.admin.passwordchecker;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class TimePickerActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button returnTimeButton;
    private String selectedTimeString;
    private TimePicker timePicker;
    private String selectedDateSave;
    private String username;
    private String nameToSave;
    private String emailToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        timePicker = (TimePicker) findViewById(R.id.TimePickerId);
        returnTimeButton = (Button) findViewById(R.id.ReturnTimeButtonId);

        returnTimeButton.setOnClickListener(this);

        Intent intentG = getIntent();
        selectedDateSave = intentG.getStringExtra("date");
        username = intentG.getStringExtra("username");
        nameToSave = intentG.getStringExtra("nameToSave");
        emailToSave = intentG.getStringExtra("emailToSave");

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.ReturnTimeButtonId)
        {
            int hour = timePicker.getHour();
            int minutes = timePicker.getMinute();
            boolean isPm = false;

            if(hour > 12)
            {
                hour -= 12;
                isPm = true;
            }
            selectedTimeString = String.valueOf(hour)+ ":";

            if(minutes == 0)
            {
                selectedTimeString += "00";
            }
            else
            {
                selectedTimeString += String.valueOf(minutes);
            }

            if(isPm)
            {
                selectedTimeString += " PM";
            }
            else
            {
                selectedTimeString += " AM";
            }


            Intent intent = new Intent(this, LoginSuccess.class);
            intent.putExtra("time",selectedTimeString);
            intent.putExtra("date",selectedDateSave);
            intent.putExtra("username",username);
            intent.putExtra("nameToSave",nameToSave);
            intent.putExtra("emailToSave",emailToSave);
            startActivity(intent);

        }
    }
}
