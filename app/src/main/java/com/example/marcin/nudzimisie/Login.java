package com.example.marcin.nudzimisie;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.marcin.nudzimisie.libraries.MyLibrary;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends Activity implements OnClickListener {

    //login.xml buttons and labels
    private EditText loginET, passwordET;
    private Button loginBt;
    private TextView registerTV_cl;


    private ProgressDialog pDialog;
    private AQuery mAQ;



    SharedPreferences userDataSharedPref;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //setup input fields
        loginET = (EditText) findViewById(R.id.login_et);
        passwordET = (EditText) findViewById(R.id.password_et);

        //setup buttons
        loginBt = (Button) findViewById(R.id.login_bt);
        registerTV_cl = (TextView) findViewById(R.id.register_tv);

        //register listeners
        loginBt.setOnClickListener(this);
        registerTV_cl.setOnClickListener(this);

        //aQuery class for Asynchronous tasks
        mAQ = new AQuery(this);

    }


    protected void onResume() {

        userDataSharedPref = getSharedPreferences(Constants.USER_DATA_PREF_FILE, Context.MODE_PRIVATE);

        if (userDataSharedPref.contains(Constants.USERNAME_PREF_KEY)) {
            if (userDataSharedPref.contains(Constants.PASSWORD_SHA1_PREF_KEY)) {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        }

        super.onResume();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_bt:
                if (MyLibrary.isOnline(Login.this)) {
                loginClicked(v);
            }
                break;
            case R.id.register_tv:
                if (MyLibrary.isOnline(Login.this)) {
                    Intent i = new Intent(this, Register.class);
                    startActivity(i);
                }
                break;

            default:
                break;
        }
    }

    private void loginClicked(View button) {

        String login = loginET.getText().toString();
        final String encryptedPassword = MyLibrary.encryptPasswordSHA1(passwordET.getText().toString());
     // final String encryptedPassword = passwordET.getText().toString(); UWAGA!!! STRONA M_LOGIN.PHP NIE BEDZIE DZIALAC Z PRZEDGLADARKI

        Map<String, String> params = new HashMap<String, String>();
        params.put(Constants.LOGIN_API_KEY, login);
        params.put(Constants.PASSWORD_API_KEY, encryptedPassword);

        pDialog = new ProgressDialog(this);

        pDialog.setIndeterminate(true); //czy nie mozna okreslic ile procent zrobione
        pDialog.setCancelable(true);
        pDialog.setInverseBackgroundForced(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage(getString(R.string.loading));

        mAQ.progress(pDialog).ajax(Constants.LOGIN_URL, params, JSONObject.class, new AjaxCallback<JSONObject>() {


            public void callback(String url, JSONObject json, AjaxStatus status) {
                if (json.optInt(Constants.SUCCESS_API_KEY, 0) == 1) {

                    Editor editor = userDataSharedPref.edit();
                    editor.putString(Constants.USERNAME_PREF_KEY, json.optString(Constants.USERNAME_API_KEY, null));
                    editor.putString(Constants.PASSWORD_SHA1_PREF_KEY, encryptedPassword);
                    editor.commit();

                    Intent i = new Intent(Login.this, MainActivity.class);
                    finish();
                    startActivity(i);


                } else {
                    Toast.makeText(getApplicationContext(), json.optString(Constants.MESSAGE_API_KEY),
                            Toast.LENGTH_LONG).show();
                }

            }

        });




    }



}
