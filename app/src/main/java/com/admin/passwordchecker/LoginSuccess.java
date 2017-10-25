package com.admin.passwordchecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

public class LoginSuccess extends AppCompatActivity
{
    private TextView welcomeLogo;
    private TextView languageView;
    private String languagePreference;
    private RadioGroup languageSelectors;
    private RadioButton englishRadio;
    private RadioButton spanishRadio;
    private EditText nameInput;
    private EditText emailAddressInput;
    private Button selectTime;
    private Button selectDate;

    private Spinner spinner;
    private ArrayAdapter<CharSequence> spinnerAdapter;

    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        welcomeLogo = (TextView) findViewById(R.id.WelcomeLogoId);
        languageView = (TextView) findViewById(R.id.LanguageViewId);
        englishRadio = (RadioButton) findViewById(R.id.EnglishRadioSelectId);
        spanishRadio = (RadioButton) findViewById(R.id.SpanishRadioSelectId);
        nameInput = (EditText) findViewById(R.id.NameInputId);
        emailAddressInput = (EditText) findViewById(R.id.EmailAddressInputId);
        selectTime = (Button) findViewById(R.id.TimeSelectButtonId);
        selectDate = (Button) findViewById(R.id.SelectDateButtonId);
    }

    private void init()
    {

        /******* Default settings ****/
        userInfo = new UserInfo();

        englishRadio.setChecked(true);
        // Create an ArrayAdapter using the string array and a default spinner layout
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.question_array_english, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(spinnerAdapter);

        languageSelectors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == englishRadio.getId()) {
                    languagePreference = "English";
                    setStringsToEnglish();

                } else if (radioGroup.getCheckedRadioButtonId() == spanishRadio.getId()) {
                    languagePreference = "Spanish";
                    setStringsToSpanish();

                }
            }
        });
    }


    private void setStringsToEnglish() {

        questionString.setText(getResources().getString(R.string.question_english));
        languagePreferencePrompt.setText(getResources().getString(R.string.lang_pref_english));
        cityPrompt.setText(getResources().getString(R.string.city_prompt_english));
        statePrompt.setText(getResources().getString(R.string.state_prompt_english));

        //Set spinner data again
        spinnerAdapter = ArrayAdapter.createFromResource(LoginSuccess.this, R.array.question_array_english, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    private void setStringsToSpanish() {

        questionString.setText(getResources().getString(R.string.question_spanish));
        languagePreferencePrompt.setText(getResources().getString(R.string.lang_pref_spanish));
        cityPrompt.setText(getResources().getString(R.string.city_prompt_spanish));
        statePrompt.setText(getResources().getString(R.string.state_prompt_spanish));

        //Set spinner data again
        spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.question_array_spanish, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    private void setUserInfoWithEnteredData() {
        String memberStatus = spinner.getSelectedItem().toString();
        userInfo.setInfo(languagePreference, memberStatus, cityField.getText().toString(), stateField.getText().toString());
        userInfo.print();
    }

    //We save all the information that was entered in into this object when you click the submit button.
    private class UserInfo {

        String languagePreference;
        String memberStatus;
        String city;
        String state;
        Date currentDate;

        public UserInfo() {

            //Sets the current date to "right now" based on phone's internal clock
            currentDate = new Date();

            //Default values
            languagePreference = "English";
            memberStatus = "Student";
            city = "Sacramento";
            state = "CA";
        }

        void setInfo(String languagePreference, String memberStatus, String city, String state) {
            this.languagePreference = languagePreference;
            this.memberStatus = memberStatus;
            this.city = city;
            this.state = state;
        }

        //For debugging purposes
        void print()  {
            Log.d("Filter", "Info "+languagePreference+" "+memberStatus+" "+city+" "+state+" "+ currentDate.toString());
        }
    }
}

