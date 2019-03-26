package com.geoideas.spooch_mobile.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.geoideas.spooch_mobile.R;
import com.geoideas.spooch_mobile.http.EventDTO;
import com.geoideas.spooch_mobile.task.Event;

import java.util.Arrays;
import java.util.List;

public class EventActivity extends AppCompatActivity {
    private static final String TAG = EventActivity.class.getSimpleName();
    private static final int MAP_INTENT_CODE = 1;
    private static final int PICTURE_INTENT_CODE = 2;
    private EditText caption, description;
    private Button gps,pic,clear,post;
    private TextView picValue, gpsValue;
    private ProgressDialog progressD;
    private double lng,lat;
    private String authHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        caption = findViewById(R.id.event_caption_fld);
        description = findViewById(R.id.event_desc_fld);
        //picValue = findViewById(R.id.event_pic_value);
        gpsValue = findViewById(R.id.event_gps_value);
        gps = findViewById(R.id.event_gps_btn);
        //pic = findViewById(R.id.event_pic_btn);
        clear = findViewById(R.id.event_clear_btn);
        post = findViewById(R.id.event_post_btn);
        SharedPreferences prefs = getSharedPreferences(Constants.PREFERENCES_FILE, MODE_PRIVATE);
        if(prefs.contains(Constants.BEARER)){
            authHeader = prefs.getString(Constants.BEARER, "");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("MapsActivity", String.valueOf(requestCode));
        Log.d("MapsActivity", String.valueOf(resultCode));
        if(resultCode == RESULT_OK && requestCode == MAP_INTENT_CODE){
            lng = data.getDoubleExtra("long",0.0);
            lat = data.getDoubleExtra("lat",0.0);
            gpsValue.setText(lng+","+lat);
        }
    }

    public void loadGPS(View view) {
       Intent intent = new Intent(this, MapsActivity.class);
       startActivityForResult(intent, MAP_INTENT_CODE);
    }

    public void loadPic(View view) {

    }

    public void post(View view) {
        progressD = ProgressDialog.show(this,"Signing in", "This will take just a few seconds");
        EventDTO event = new EventDTO(caption.getText().toString(), description.getText().toString(), Arrays.<Double>asList(lng,lat));
        new Event(this, event, authHeader,getString(R.string.spooch_host)).execute();
    }


    public void afterPost(boolean success,String message){
        this.progressD.dismiss();
        if(success) message = "Event Successfully Posted";
        Snackbar.make(this.getCurrentFocus(), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }

    public void clear(View view) {
        caption.setText("");
        description.setText("");
        //picValue.setText("");
        gpsValue.setText("");
    }

}
