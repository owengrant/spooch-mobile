package com.geoideas.spooch_mobile.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.geoideas.spooch_mobile.R;
import com.geoideas.spooch_mobile.task.Login;

public class LoginActivity extends AppCompatActivity {
    private final static String TAG = LoginActivity.class.getSimpleName();

    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    public void signIn(View view) {
        Log.d(TAG, username.getText().toString());
        Log.d(TAG, password.getText().toString());
        String uName = username.getText().toString();
        String pWord = password.getText().toString();
        new Login(this, uName, pWord).execute();
    }

    public void register(View view) {
        startActivity(new Intent(this, RegisterUserActivity.class));
    }
}
