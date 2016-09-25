package com.example.marcin.nudzimisie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ListaTras extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tras);
    }

    public void pierwszaTrasa(View v){
        Intent i = new Intent(this, PierwszaTrasa.class);
        startActivity(i);

    }

    public void drugaTrasa(View v){
        Intent a = new Intent(this, DrugaTrasa.class);
        startActivity(a);

    }

}
