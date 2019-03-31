package org.aossie.agoravote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

public class MainActivity extends AppCompatActivity {

    SharedPrefs sharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefs = new SharedPrefs(getApplicationContext());
        Log.d("heyuser", sharedPrefs.getLogedInKey());
        doSignOut();

    }

    private void doSignOut() {

        AndroidNetworking.get("https://agora-rest-api.herokuapp.com/api/v1/user/logout")
                .addHeaders("X-Auth-Token", sharedPrefs.getLogedInKey())// posting json
                .addHeaders("accept", "application/json")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        // do anything with response
                        Log.d("response", "" + response);
                        finish();
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorb", "" + error.getErrorBody());
                        Log.d("errorr", "" + error.getResponse());
                        Log.d("errord", "" + error.getErrorDetail());
                        Log.d("errorc", "" + error.getErrorCode());
                        Log.d("errorm", "" + error.getMessage());
                    }
                });
    }
}
