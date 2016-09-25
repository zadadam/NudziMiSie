package com.example.marcin.nudzimisie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;

public class ChcePozwiedzacConfig extends AppCompatActivity {

    //tu definicja filtru kategorii
    Boolean sportowyChecked = false;
    Boolean zabawaChecked = false;
    Boolean zamekChecked = false;
    Boolean militarneChecked = false;
    Boolean przemysloweChecked = false;
    Boolean mostChecked = false;
    Boolean cmentarzChecked = false;
    Boolean mieszkalneChecked = false;
    Boolean architekturaChecked = false;
    Boolean malaArchitekturaChecked = false;

    //parametry filtru zmienne
    SharedPreferences userFilterData;



    private SeekBar seekBarCzas;
    private TextView czasTekst;
    private SeekBar seekBarPieniadze;
    private TextView pieniadzeTekst;
    private ImageView parkImage;
    private ImageView zamekImage;
    private ImageView sakralneImage;
    private ImageView malaArchitekturaImage;
    public Integer ileGodzin_form;
    public Integer ilePieniedzy_form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chce_pozwiedzac_config);

        czasTekst = (TextView) findViewById(R.id.czasTekst);
        seekBarCzas = (SeekBar) findViewById(R.id.seekBarCzas);
        pieniadzeTekst = (TextView) findViewById(R.id.pienidzeTekst);
        seekBarPieniadze = (SeekBar) findViewById(R.id.seekBarPieniadze);
        parkImage = (ImageView) findViewById(R.id.parki);
        zamekImage= (ImageView) findViewById(R.id.dworki);
        sakralneImage = (ImageView) findViewById(R.id.sakralne);
        malaArchitekturaImage= (ImageView) findViewById(R.id.malaarchitek);

        parkImage.setOnClickListener(sportHandler);
        zamekImage.setOnClickListener(zamekHandler);
        sakralneImage.setOnClickListener(militarneHandler);
        malaArchitekturaImage.setOnClickListener(malaArchitekturaHandler);

        czasTekst.setText("0h");
        pieniadzeTekst.setText("0zł");


        seekBarCzas.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int ileGodzin = 0;

            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                ileGodzin = progresValue;
                czasTekst.setText(ileGodzin + "h");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                czasTekst.setText(ileGodzin + "h");
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                czasTekst.setText(ileGodzin + "h");
                ileGodzin_form = ileGodzin;
            }
        });
        seekBarPieniadze.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int ilePieniedzy = 0;

            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                ilePieniedzy = progresValue*10;
                pieniadzeTekst.setText(ilePieniedzy + "zł");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                pieniadzeTekst.setText(ilePieniedzy + "zł");
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                pieniadzeTekst.setText(ilePieniedzy + "zł");
                ilePieniedzy_form=ilePieniedzy;
            }
        });
    }

    View.OnClickListener sportHandler = new View.OnClickListener() {

        public void onClick(View v) {

                    if(!sportowyChecked) {
                        parkImage.setBackgroundResource(R.drawable.parki);
                        sportowyChecked=true;
                    } else{
                        parkImage.setBackgroundResource(R.drawable.parki1);
                        sportowyChecked=false;
                    }
        }
    };
    View.OnClickListener zamekHandler = new View.OnClickListener() {

        public void onClick(View v) {

            if(!zamekChecked) {
                zamekImage.setBackgroundResource(R.drawable.dworki);
                zamekChecked=true;
            } else{
                zamekImage.setBackgroundResource(R.drawable.dworki1);
                zamekChecked=false;
            }
        }
    };
    View.OnClickListener militarneHandler = new View.OnClickListener() {

        public void onClick(View v) {

            if(!militarneChecked) {
                sakralneImage.setBackgroundResource(R.drawable.sakralne);
                militarneChecked=true;
            } else{
                sakralneImage.setBackgroundResource(R.drawable.sakralne1);
                militarneChecked=false;
            }
        }
    };
    View.OnClickListener malaArchitekturaHandler = new View.OnClickListener() {

        public void onClick(View v) {

            if(!malaArchitekturaChecked) {
                malaArchitekturaImage.setBackgroundResource(R.drawable.malaarchitek);
                malaArchitekturaChecked=true;
            } else{
                malaArchitekturaImage.setBackgroundResource(R.drawable.malaarchitek1);
                malaArchitekturaChecked=false;
            }
        }
    };

    public void go(View v){

        userFilterData = getSharedPreferences("USER_FILTER_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userFilterData.edit();
        editor.putString("CZAS_DOSTEPNY", String.valueOf(ileGodzin_form));
        editor.putString("BUDZET_DOSTEPNY", String.valueOf(ilePieniedzy_form));
        editor.commit();


        Intent i = new Intent(this, ListaTras.class);
        startActivity(i);
    }
    }

