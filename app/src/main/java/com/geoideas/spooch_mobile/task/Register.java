package com.geoideas.spooch_mobile.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.geoideas.spooch_mobile.http.RegisteredUser;
import com.geoideas.spooch_mobile.http.ReturnedId;
import com.geoideas.spooch_mobile.http.SpoochApi;
import com.geoideas.spooch_mobile.http.SpoochClient;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;

public class Register extends AsyncTask<Void, Void, Void> {

    private static final String TAG = Register.class.getSimpleName();

    private WeakReference<Activity> master;
    private String username, password, confirmPassword;
    private final String BASE_URL;
    private ProgressDialog progressD;
    private String message;
    private boolean success;

    public Register(Activity master,String username, String password, String confirmPassword, final String BASE_URL){
        super();
        this.master = new WeakReference<>(master);
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.BASE_URL = BASE_URL;
    }

    @Override
    protected void onPreExecute() {
        this.progressD = ProgressDialog.show(master.get(), "Registering new user"," This will take just a few seconds");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        SpoochApi api = new SpoochClient(BASE_URL).create();
        Call<Void> call = api.registerUser(new RegisteredUser(username, password, confirmPassword));
        try {
            final Response<Void> res = call.execute();
            if(res.isSuccessful() && res.code() == 201) {
                message = "Registration Successful";
                success = true;
            }
            else message = res.errorBody().string();

        } catch (IOException e) {
            message = "Network Error";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressD.dismiss();
        Snackbar.make(master.get().getCurrentFocus(), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }
}
