package com.example.marcin.nudzimisie.libraries;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.marcin.nudzimisie.Constants;
import com.example.marcin.nudzimisie.JSONParser;
import com.example.marcin.nudzimisie.Login;
import com.example.marcin.nudzimisie.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class MyLibrary {


    //hashuje hasło do SHA1
    public static String encryptPasswordSHA1(String password)
    {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return sha1;
    }

    //potrzebne do funkcji endryptPassword
    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    //sprawdza czy zapisane dane uzytkownika sa poprawne, jak nie sa to usuwa sharedpref i przechodzi do loginu, NA WEJSCIU ACTIVITY CONTEXT VIEW (nazwaklasy.this)
    public static void checkPass(final Context ctx){

        new AsyncTask<Void, Void, Void>(){

            protected Void doInBackground(Void... params) {

                int success;
                SharedPreferences userDataSharedPref = ctx.getSharedPreferences(Constants.USER_DATA_PREF_FILE, Context.MODE_PRIVATE);
                JSONParser jsonParser = new JSONParser();


                try {
                    // Building Parameters
                    List<NameValuePair> paramsL = new ArrayList<NameValuePair>();
                    paramsL.add(new BasicNameValuePair(Constants.LOGIN_API_KEY, userDataSharedPref.getString(Constants.USERNAME_PREF_KEY, null)));
                    paramsL.add(new BasicNameValuePair(Constants.PASSWORD_API_KEY, userDataSharedPref.getString(Constants.PASSWORD_SHA1_PREF_KEY, null)));

                    Log.d("request!", "starting static SSSSSSSSSSSSSSSSSSS");
                    // getting product details by making HTTP request
                    JSONObject json = jsonParser.makeHttpRequest(
                            Constants.LOGIN_URL, "POST", paramsL);

                    // check your log for json response
                    Log.d("Login attempt", json.toString());

                    // json success tag
                    success = json.getInt(Constants.SUCCESS_API_KEY);

                    if (success == 1) {

                        Log.d("Dane poprawne", "OKKKKKKKKKKKKKKKKKKKKKKKKKKKK");


                    }else{

                        SharedPreferences.Editor editor = userDataSharedPref.edit();
                        editor.clear();
                        editor.commit();
                        Intent i = new Intent(ctx, Login.class);
                        ((Activity) ctx).finish();
                        ctx.startActivity(i);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
                return null;

            }
        }.execute();

    }

    //sprawdza czy telefon ma połączenie z internetem, trzeba przekazać kontekst
    public static boolean isOnline(Context context){


            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            }

            int duration = Toast.LENGTH_SHORT;
            String message = context.getString(R.string.no_internet_to);
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();
            return false;


    }


}
