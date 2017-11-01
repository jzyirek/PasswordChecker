package com.admin.passwordchecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PasswordCheckerActivity extends AppCompatActivity implements OnClickListener
{
    private EditText UsernameInputId;
    private EditText PasswordInputId;
    private Button  SubmitButtonId;
    private String username = "jzyirek";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_checker);

        UsernameInputId = (EditText) findViewById(R.id.UsernameInputId);
        PasswordInputId = (EditText) findViewById(R.id.PasswordInputId);
        SubmitButtonId = (Button) findViewById(R.id.SubmitButtonId);

        SubmitButtonId.setOnClickListener(this);
    }


    public void verifyPassword()
    {
        String validPassword = "calculate";
        String userIdTest = UsernameInputId.getText().toString();
        String passwordIdTest = PasswordInputId.getText().toString();

        if(userIdTest.equals(username))
        {
            if(passwordIdTest.equals(validPassword))
            {
                loginSuccess(username);
            }
        }
        else
        {
            PasswordInputId.getHint();
        }
    }

    public void loginSuccess(String username)
    {
        Intent intent = new Intent(PasswordCheckerActivity.this, LoginSuccess.class);
        intent.putExtra(UsernameInputId.toString(),username);
        startActivity(intent);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.SubmitButtonId)
        {
            verifyPassword();
        }
    }
}
