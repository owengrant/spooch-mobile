package com.geoideas.spooch_mobile.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class Login extends AsyncTask<Void, Void, Void> {

    private WeakReference<Activity> master;
    private String username, password;
    private ProgressDialog progressD;

    public Login(Activity master, String username, String password){
        super();
        this.master = new WeakReference<>(master);
        this.username = username;
        this.password = password;
    }

    @Override
    protected void onPreExecute() {
        this.progressD = ProgressDialog.show(this.master.get(),"Signing in", "This will take just a few seconds");
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
        progressD.dismiss();
    }
}
