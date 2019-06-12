package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    private TextView login, password, submit_2,back;
    private Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        database= new Database(this);



        login= findViewById(R.id.email_login);
        password=findViewById(R.id.password_login);
        submit_2=findViewById(R.id.submit_1);

        submit_2.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if (validate()) {
                                              shared();

                                          } else {
                                              Toast.makeText(LoginActivity.this, " Sign-Up Unsuccessful! ", Toast.LENGTH_LONG).show();
                                          }
                                      }
                                  }

        );
    }

    private void shared() {


        String email_1= login.getText().toString();
        String pass_1= password.getText().toString();

        SharedPreferences prefs = getSharedPreferences("shared_preference", MODE_PRIVATE);
        String e_email = prefs.getString(Constant.EMAIL, "xyz@gmail.com");
        String p_pass = prefs.getString(Constant.PASSWORD, "xxxxxxxx");

        if(e_email.equals(email_1) && p_pass.equals(pass_1) ){
            Toast.makeText(this," Login Successful! ",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this," E-mail or Password Incorrect! ",Toast.LENGTH_LONG).show();
        }
    }



    private boolean validate(){

        boolean log_1=true, pass_1 = true;

        if(login.getText().length()<5 || login.getText().length()>40 || login.getText().length()==0){
            login.setError("This field should not exceed the limit of 40 digits.");
            log_1=false;
        }
        if(password.getText().length()<8 || password.getText().length()>20 || password.getText().length()==0){
            password.setError("Password length must be between 8 to 20.");
            pass_1=false;
        }

        return  log_1 && pass_1;

    }

}