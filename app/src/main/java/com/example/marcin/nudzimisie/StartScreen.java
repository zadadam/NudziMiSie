package com.example.marcin.nudzimisie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StartScreen extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton1:
                System.out.println("aaaaaa");
                break;
            case R.id.imageButton2:

                break;
            case R.id.imageButton3:

                break;
            case R.id.imageButton4:

                break;

            default:
                break;
        }}

        public void settingsButton(View v)
        {
            //Toast.makeText(this, "Show some text on the screen.", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        public void firstButton(View v)
        {
            //Toast.makeText(this, "Show some text on the screen.", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, ChcePozwiedzacConfig.class);
            startActivity(i);
        }
        public void secondButton(View v)
        {
            //Toast.makeText(this, "Show some text on the screen.", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

    }

