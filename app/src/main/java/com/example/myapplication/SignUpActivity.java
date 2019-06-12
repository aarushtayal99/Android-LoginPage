/*
    Link to refer: https://guides.codepath.com/android/using-the-app-toolbar#displaying-an-app-icon
    Toolbar to react with scrolling down: toolbar disappears* find in the referred link
*/

package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {


    private TextView fName, lName, email, contact, pass_1, next;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if (validate()) {
                                              if (!hasAccountCreated()) {
                                                  saveToSharedPref();
                                                  Toast.makeText(SignUpActivity.this, " Sign-Up Successful! ", Toast.LENGTH_LONG).show();
                                                  startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                              }
                                          } else {
                                              Toast.makeText(SignUpActivity.this, " " +
                                                      "Sign-Up Unsuccessful! ", Toast.LENGTH_LONG).show();
                                          }
                                      }
                                  }
        );
    }

    private void initView() {
        fName = findViewById(R.id.fname);
        lName = findViewById(R.id.lname);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        submit = findViewById(R.id.submit);
        pass_1 = findViewById(R.id.pass);
    }

    private boolean hasAccountCreated() {
        String contact_2 = contact.getText().toString();
        String email_2 = email.getText().toString();
        SharedPreferences prefs = getSharedPreferences("shared_preference", MODE_PRIVATE);
        String c_contact = prefs.getString(Constant.CONTACT, "7895xxxxxx");
        String e_email = prefs.getString(Constant.EMAIL, "xyz@gmail.com");
        if (contact_2.equals(c_contact)) {
            contact.setError("Contact already exists.");
            return true;
        }
        if (email_2.equals(e_email)) {
            email.setError("Email already exists.");
            return true;
        }
        return false;
    }

    private void saveToSharedPref() {
        String fname_1 = fName.getText().toString();
        String lname_1 = lName.getText().toString();
        String contact_2 = contact.getText().toString();
        String email_2 = email.getText().toString();
        String pass_3 = pass_1.getText().toString();
        SharedPreferences.Editor editor = getSharedPreferences("shared_preference", MODE_PRIVATE).edit();
        editor.putString(Constant.FIRST_NAME, fname_1);
        editor.putString(Constant.LAST_NAME, lname_1);
        editor.putString(Constant.CONTACT, contact_2);
        editor.putString(Constant.EMAIL, email_2);
        editor.putString(Constant.PASSWORD, pass_3);
        editor.apply();
    }









    private boolean validate() {
        boolean fname = true, lname = true, email_1 = true, contact_1 = true, pass_2 = true;
        if (fName.getText().length() < 5 || fName.getText().length() > 16) {

            fName.setError("Length should be between 5 and 16.");
            fname = false;

        }
        if (lName.getText().length() < 5 || lName.getText().length() > 16) {

            lName.setError("Length should be between 5 and 16.");
            lname = false;
        }
        if (contact.getText().length() != 10) {

            contact.setError("This field should contain at least 10 digits.");
            email_1 = false;
        }

        if (email.getText().length() < 10 || email.getText().length() > 30) {

            email.setError("The field cannot exceed the limit of 40 digits.");
            contact_1 = false;
        }

        if (pass_1.getText().length() < 8 || pass_1.getText().length() > 20) {

            pass_1.setError("Password length must be between 8 to 20.");
            pass_2 = false;
        }

        return fname && lname && email_1 && contact_1 && pass_2;

    }



}
