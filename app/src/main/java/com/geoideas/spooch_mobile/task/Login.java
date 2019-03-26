package com.geoideas.spooch_mobile.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.geoideas.spooch_mobile.activity.LoginActivity;
import com.geoideas.spooch_mobile.http.ReturnedToken;
import com.geoideas.spooch_mobile.http.SpoochApi;
import com.geoideas.spooch_mobile.http.SpoochClient;
import com.geoideas.spooch_mobile.http.UserLogin;

import java.io.IOException;
import java.lang.ref.WeakReference;

import retrofit2.Response;

public class Login extends AsyncTask<Void, Void, Void> {

    private WeakReference<LoginActivity> master;
    private String username, password;
    private final String BASE_URL;
    private ProgressDialog progressD;
    private String token = "";
    private boolean success;

    public Login(LoginActivity master, String username, String password, String base){
        super();
        this.master = new WeakReference<>(master);
        this.username = username;
        this.password = password;
        this.BASE_URL = base;
    }

    @Override
    protected void onPreExecute() {
        this.progressD = ProgressDialog.show(this.master.get(),"Signing in", "This will take just a few seconds");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        SpoochApi api = new SpoochClient(BASE_URL).create();
        try {
            Response<ReturnedToken> res = api.login(new UserLogin(username,password)).execute();
            if(res.isSuccessful()) {
                token = res.body().getToken();
                success = true;
            } else token = res.message();
        } catch (IOException e) {
            token = "Network Error";
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    protected void onPostExecute(Void aVoid) {
        progressD.dismiss();
        if(success) master.get().saveToken(token);
        else Snackbar.make(master.get().getCurrentFocus(), token, Snackbar.LENGTH_LONG)
                     .setAction("Action", null)
                     .show();
    }
}
