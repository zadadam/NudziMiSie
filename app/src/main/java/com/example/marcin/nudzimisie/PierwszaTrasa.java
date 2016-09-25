package com.example.marcin.nudzimisie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class PierwszaTrasa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pierwsza_trasa);

        ImageView img = (ImageView)findViewById(R.id.go_1);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.google.pl/maps/dir/plac+Teatralny+4,+Wroc%C5%82aw/Opera+Wroc%C5%82awska/Teatralna+10,+50-055+Wroc%C5%82aw/%C5%9Awidnicka+23,+Wroc%C5%82aw/Mennicza+25,+50-057+Wroc%C5%82aw/@51.1057128,17.0319368,17z/data=!4m32!4m31!1m5!1m1!1s0x470fc27142bd7cdd:0xef2ae57ffc96e1df!2m2!1d17.0330971!2d51.1051273!1m5!1m1!1s0x0:0xf87a661b2aae2d32!2m2!1d17.030619!2d51.1056925!1m5!1m1!1s0x470fc270da3a8739:0xab11e352c2441f83!2m2!1d17.0364797!2d51.1051177!1m5!1m1!1s0x470fc27409daf0a1:0x305408421c8e9750!2m2!1d17.0316472!2d51.1066717!1m5!1m1!1s0x470fc27132c1e05b:0x534e55e7c5c25101!2m2!1d17.0343589!2d51.1061502!3e2"));
                startActivity(intent);
            }
        });
}}
