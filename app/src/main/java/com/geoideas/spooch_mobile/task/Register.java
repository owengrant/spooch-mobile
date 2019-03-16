package com.geoideas.spooch_mobile.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class Register extends AsyncTask<Void, Void, Void> {

    private WeakReference<Activity> master;
    private String username, password, confirmPassword;
    private ProgressDialog progressD;

    public Register(Activity master,String username, String password, String confirmPassword){
        super();
        this.master = new WeakReference<>(master);
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Override
    protected void onPreExecute() {
        this.progressD = ProgressDialog.show(master.get(), "Registering new user"," This will take just a few seconds");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
