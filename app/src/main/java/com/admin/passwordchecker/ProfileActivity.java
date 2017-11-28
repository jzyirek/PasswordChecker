package com.admin.passwordchecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener

{
    private Button linkToApp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        linkToApp = (Button) findViewById(R.id.TicTacToeLinkButtonId);

        linkToApp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.TicTacToeLinkButtonId)
        {
            //implement link to app from here
        }
    }
}
