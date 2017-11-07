package com.admin.passwordchecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener


{
    private Button returnDateButton;
    private String DATE_MESSAGE = "com.admin.LoginSuccess";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        returnDateButton = (Button) findViewById(R.id.ReturnDateButtonId);

    }

    @Override
    public void onClick(View view)
    {
        Intent intent = new Intent(this, LoginSuccess.class);
        //intent.putExtra("date",)
        startActivity(intent);

    }
}
