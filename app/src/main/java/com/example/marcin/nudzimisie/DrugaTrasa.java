package com.example.marcin.nudzimisie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class DrugaTrasa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.druga_trasa);

        ImageView img = (ImageView)findViewById(R.id.go_2);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.google.pl/maps/dir/Ogr%C3%B3d+Botaniczny/Katedra+%C5%9Bw.+Jana+Chrzciciela,+plac+Katedralny+18,+50-329+Wroc%C5%82aw/Ko%C5%9Bci%C3%B3%C5%82+%C5%9Awi%C4%99tego+Krzy%C5%BCa/Biblioteka+Uniwersytecka+Na+Piasku/51.1160871,17.0394565/51.1161878,17.0353014/51.1170227,17.0387716/@51.1150162,17.0377821,16.12z/data=!4m29!4m28!1m5!1m1!1s0x0:0xbcbbf1251bac1bed!2m2!1d17.0463781!2d51.1152129!1m5!1m1!1s0x470fe9d7503b8439:0xde5330fc2110d376!2m2!1d17.0465814!2d51.1141738!1m5!1m1!1s0x0:0x2f993641fc5e1ebb!2m2!1d17.0440364!2d51.1149753!1m5!1m1!1s0x0:0x14b768a9f7e54ecc!2m2!1d17.040885!2d51.1144637!1m0!1m0!1m0!3e2"));
                startActivity(intent);
            }
        });
}}
