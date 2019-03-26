package com.geoideas.spooch_mobile.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.geoideas.spooch_mobile.activity.EventActivity;
import com.geoideas.spooch_mobile.http.EventDTO;
import com.geoideas.spooch_mobile.http.ReturnedId;
import com.geoideas.spooch_mobile.http.SpoochClient;

import java.io.IOException;
import java.lang.ref.WeakReference;

import retrofit2.Response;

public class Event extends AsyncTask<Void, Void, Void> {

    private WeakReference<EventActivity> master;
    private EventDTO event;
    private String authHeader;
    private final String BASE_URL;
    private String message;
    private boolean success;

    public Event(EventActivity activity, EventDTO event, String authHeader,String BASE_URL) {
        this.master = new WeakReference<>(activity);
        this.event = event;
        this.BASE_URL = BASE_URL;
        this.authHeader = authHeader;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Response<ReturnedId> res = null;
        try {
            res = new SpoochClient(BASE_URL).create().postEvent(authHeader,event).execute();
            if(res.isSuccessful()){
                message = res.body().getId();
                success = true;
            } else message = res.errorBody().string();
        } catch (IOException e) {
            message = "Network Error";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
       master.get().afterPost(success,message);
    }
}
