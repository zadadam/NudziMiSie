package com.example.marcin.nudzimisie;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Initialising_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialising_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(Initialising_activity.this, ChcePozwiedzacConfig.class);
                Initialising_activity.this.startActivity(mainIntent);
                Initialising_activity.this.finish();
            }
        }, 3000);

    }

}
