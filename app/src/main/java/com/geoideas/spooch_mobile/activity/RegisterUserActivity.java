package com.geoideas.spooch_mobile.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.geoideas.spooch_mobile.R;
import com.geoideas.spooch_mobile.task.Register;


public class RegisterUserActivity extends AppCompatActivity {

    private EditText username, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        this.username = findViewById(R.id.username);
        this.password = findViewById(R.id.password);
        this.confirmPassword = findViewById(R.id.confirm_password);
    }

    public void clear(View view) {
        this.username.setText("");
        this.password.setText("");
        this.confirmPassword.setText("");
    }

    public void register(View view) {
        String uName = this.username.getText().toString();
        String pWord = this.password.getText().toString();
        String cpWord = this.confirmPassword.getText().toString();
        new Register(this, uName, pWord, cpWord, getString(R.string.spooch_host)).execute();
    }
}
