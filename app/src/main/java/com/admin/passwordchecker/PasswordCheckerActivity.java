package com.admin.passwordchecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class PasswordCheckerActivity extends AppCompatActivity implements OnEditorActionListener
{
    private EditText UsernameInputId;
    private EditText PasswordInputId;
    private TextView PasswordCorrectId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_checker);

        UsernameInputId = (EditText) findViewById(R.id.UsernameInputId);
        PasswordInputId = (EditText) findViewById(R.id.PasswordInputId);
        PasswordCorrectId = (TextView) findViewById(R.id.PasswordCorrectId);

        PasswordInputId.setOnEditorActionListener(this);
    }

    public void verifyPassword()
    {
        String username = "jzyirek";
        String validPassword = "calculate";
        String correctPassword = "Correct!";
        String incorrectPassword = "Wrong!";
        String userIdTest = UsernameInputId.getText().toString();
        String passwordIdTest = PasswordInputId.getText().toString();

        if(userIdTest.equals(username))
        {
            if(passwordIdTest.equals(validPassword))
            {
                PasswordCorrectId.setText(correctPassword);
            }
            else
            {
                PasswordCorrectId.setText(incorrectPassword);
            }
        }
        else
        {
            PasswordCorrectId.setText("Invalid Username");
        }
    }
    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
    {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
            actionId == EditorInfo.IME_ACTION_UNSPECIFIED ||
            event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
        {
            verifyPassword();
        }
        return false;
    }
}
