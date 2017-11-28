package com.admin.passwordchecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

public class LoginSuccess extends AppCompatActivity implements View.OnClickListener
{
    private TextView welcomeLogo;
    private TextView languageView;
    private TextView questionString;
    private TextView name;
    private TextView emailAddress;

    private RadioGroup languageSelectors;
    private RadioButton englishRadio;
    private RadioButton spanishRadio;

    private EditText nameInput;
    private EditText emailAddressInput;
    private EditText date;
    private EditText time;

    private Button selectTime;
    private Button selectDate;
    private Button submitButton;

    private String message;
    private String languagePreference;
    private String dateSelected;
    private String timeSelected;
    private String nameToSave;
    private String emailToSave;

    private Spinner personClassification;
    private ArrayAdapter<CharSequence> spinnerAdapter;

    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        welcomeLogo = (TextView) findViewById(R.id.WelcomeLogoId);
        languageView = (TextView) findViewById(R.id.LanguageViewId);
        languageSelectors = (RadioGroup) findViewById(R.id.languageSelectors) ;
        englishRadio = (RadioButton) findViewById(R.id.EnglishRadioSelectId);
        spanishRadio = (RadioButton) findViewById(R.id.SpanishRadioSelectId);
        questionString = (TextView) findViewById(R.id.ClassificationLabelId);
        name = (TextView) findViewById(R.id.NameLabelId);
        emailAddress = (TextView) findViewById(R.id.EmailAddressLabelId);
        personClassification = (Spinner) findViewById(R.id.PersonClassificationId);

        date = (EditText) findViewById(R.id.DateEditTextId);
        time = (EditText) findViewById(R.id.TimeEditTextId);

        selectTime = (Button) findViewById(R.id.TimeSelectButtonId);
        selectDate = (Button) findViewById(R.id.SelectDateButtonId);
        submitButton = (Button) findViewById(R.id.SubmitButton2Id);

        selectDate.setOnClickListener(this);
        selectTime.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        nameInput = (EditText) findViewById(R.id.NameInputId);
        emailAddressInput = (EditText) findViewById(R.id.EmailAddressInputId);


        Intent intent = getIntent();
        message = intent.getStringExtra(PasswordCheckerActivity.EXTRA_MESSAGE);
        timeSelected = intent.getStringExtra("time");
        dateSelected = intent.getStringExtra("date");
        nameToSave = intent.getStringExtra("nameToSave");
        emailToSave = intent.getStringExtra("emailToSave");

        if(message == null)
        {
            message = intent.getStringExtra("username");
        }

        welcomeLogo.setText("Welcome " + message);
        if(dateSelected != null)
        {
            date.setText(dateSelected.toString());
        }

        if(timeSelected != null)
        {
            time.setText(timeSelected);
        }

        if(nameToSave != null)
        {
            nameInput.setText(nameToSave.toString());
        }

        if(emailToSave != null)
        {
            emailAddressInput.setText(emailToSave.toString());
        }
        init();
    }

    private void init()
    {

//        /******* Default settings ****/
        userInfo = new UserInfo();

        englishRadio.setChecked(true);
        // Create an ArrayAdapter using the string array and a default spinner layout
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.question_array_english, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
        personClassification.setAdapter(spinnerAdapter);

        languageSelectors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                if (radioGroup.getCheckedRadioButtonId() == englishRadio.getId())
                {
                    languagePreference = "English";
                    setStringsToEnglish();

                } else if (radioGroup.getCheckedRadioButtonId() == spanishRadio.getId())
                {
                    languagePreference = "Spanish";
                    setStringsToSpanish();

                }
            }
        });
    }


    private void setStringsToEnglish() {

        questionString.setText("Please Select One:");
        languageView.setText("Language:");
        name.setText("Name:");
        emailAddress.setText("Email Address:");
        welcomeLogo.setText("Welcome " + message);
        selectTime.setText("Time");
        selectDate.setText("Date");

        //Set spinner data again
        spinnerAdapter = ArrayAdapter.createFromResource(LoginSuccess.this, R.array.question_array_english, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        personClassification.setAdapter(spinnerAdapter);
    }

    private void setStringsToSpanish()
    {

        questionString.setText("Por favor, seleccione uno:");
        languageView.setText("Idomia:");
        name.setText("Nombre:");
        emailAddress.setText("Dirección de correo electrónico:");
        welcomeLogo.setText("Bienvenido " + message);
        selectDate.setText("Fecha");
        selectTime.setText("Hora");

        //Set spinner data again
        spinnerAdapter = ArrayAdapter.createFromResource(LoginSuccess.this, R.array.question_array_spanish, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        personClassification.setAdapter(spinnerAdapter);
    }

    private void setUserInfoWithEnteredData()
    {
        String classification = personClassification.getSelectedItem().toString();
        userInfo.setInfo(languagePreference, classification, name.getText().toString(), emailAddress.getText().toString());
        userInfo.print();
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.TimeSelectButtonId)
        {
            selectTime();
        }
        else if (view.getId() == R.id.SelectDateButtonId)
        {
            selectDate();
        }
        else if (view.getId() == R.id.SubmitButton2Id)
        {
            submit();
        }
    }

    private void submit()
    {
        setUserInfoWithEnteredData();
        Intent intent = new Intent(LoginSuccess.this, ProfileActivity.class);
        intent.putExtra("username",message);
        if(nameInput != null)
        {
            intent.putExtra("nameToSave",nameInput.getText().toString());
        }

        if(emailAddressInput != null)
        {
            intent.putExtra("emailToSave",emailAddressInput.getText().toString());
        }

        if(time != null)
        {
            intent.putExtra("time",time.getText().toString());
        }
        if(date != null)
        {
            intent.putExtra("date",date.getText().toString());
        }
        startActivity(intent);

    }

    private void selectDate()
    {
        Intent intent = new Intent(LoginSuccess.this, DatePickerActivity.class);
        intent.putExtra("username",message);

        if(nameInput != null)
        {
            intent.putExtra("nameToSave",nameInput.getText().toString());
        }

        if(emailAddressInput != null)
        {
            intent.putExtra("emailToSave",emailAddressInput.getText().toString());
        }

        if(time != null)
        {
            intent.putExtra("time",time.getText().toString());
        }
        startActivity(intent);

    }

    private void selectTime()
    {
        Intent intent = new Intent(LoginSuccess.this, TimePickerActivity.class);
        intent.putExtra("username",message);
        if(nameInput != null)
        {
            intent.putExtra("nameToSave",nameInput.getText().toString());
        }

        if(emailAddressInput != null)
        {
            intent.putExtra("emailToSave",emailAddressInput.getText().toString());
        }


        if(date != null)
        {
            intent.putExtra("date",date.getText().toString());
        }
        startActivity(intent);

    }

    //We save all the information that was entered in into this object when you click the submit button.
    private class UserInfo
    {

        String languagePreference;
        String name;
        String email;
        String classification;
        Date currentDate;

        public UserInfo()
        {

            //Sets the current date to "right now" based on phone's internal clock
            currentDate = new Date();

            //Default values
            languagePreference = "English";
            classification = "Student";
            name = "";
            email = "";
        }

        void setInfo(String languagePreference, String classification, String name, String email)
        {
            this.classification = classification;
            this.languagePreference = languagePreference;
            this.name = name;
            this.email = email;
        }

        //For debugging purposes
        void print()  {
            Log.d("Filter", "Info "+languagePreference+" "+classification+" "+name+" "+email+" "+ currentDate.toString());
        }
    }
}

