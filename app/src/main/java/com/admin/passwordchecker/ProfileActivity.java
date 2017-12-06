package com.admin.passwordchecker;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener

{
    private Button linkToApp;
    private String selectedTimeSave;
    private String dateSelected;
    private String nameToSave;
    private String emailToSave;
    private Button NextId;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        linkToApp = (Button) findViewById(R.id.TicTacToeLinkButtonId);
        NextId = (Button) findViewById(R.id.NextId);

        NextId.setOnClickListener(this);
        linkToApp.setOnClickListener(this);

        Intent intentG = getIntent();
        dateSelected = intentG.getStringExtra("date");
        selectedTimeSave = intentG.getStringExtra("time");
        nameToSave = intentG.getStringExtra("nameToSave");
        emailToSave = intentG.getStringExtra("emailToSave");
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.NextId)
        {
            String app = "https://play.google.com/store/apps/details?id=com.admin.finalproject";
            String [] to = {"abmukarram@gmail.com"};
            String [] cc = {emailToSave};
            String text = nameToSave;

            text += " you have an appointment on " + dateSelected + " at " + selectedTimeSave + "\n" + app;

            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
            emailIntent.putExtra(Intent.EXTRA_CC,cc);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT," Sent from my app!");
            emailIntent.putExtra(Intent.EXTRA_TEXT,text);
            emailIntent.setType("message/rfc822");

            try {
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No Mail App", Toast.LENGTH_LONG).show();
                System.out.println("Mail Not there");
            }

        }
    }
}
