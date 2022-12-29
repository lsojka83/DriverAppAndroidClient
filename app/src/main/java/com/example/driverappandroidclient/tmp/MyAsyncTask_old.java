package com.example.driverappandroidclient.tmp;

import android.os.AsyncTask;

import com.example.driverappandroidclient.FirstFragment;
import com.example.driverappandroidclient.converter.JsonRederToAdviceConverter;
import com.example.driverappandroidclient.model.Advice;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyAsyncTask_old extends AsyncTask<String, String, List<Advice>> {

    private final FirstFragment firstFragment;

    public MyAsyncTask_old(FirstFragment firstFragment) {
        this.firstFragment = firstFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected List<Advice> doInBackground(String... params) {

        List<Advice> advices = new ArrayList<>();

        try {
            URL apiEndpoint = new URL("http://10.0.2.2:8080/advices/");
            HttpURLConnection myConnection =
                    (HttpURLConnection) apiEndpoint.openConnection();
//                    myConnection.setRequestProperty("User-Agent", "abc");
//                    myConnection.setRequestProperty("Accept",
//                            "application/vnd.github.v3+json");
//                    myConnection.setRequestProperty("Contact-Me",
//                            "hathibelagal@example.com");


            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here
                InputStream responseBody = myConnection.getInputStream();
                advices = new JsonRederToAdviceConverter().readJsonStream(responseBody);


//                    System.out.println(advices.get(0).adviceText);
//                    System.out.println(advices.get(1).image.link);

                myConnection.disconnect();


            } else {
                // Error handling code goes here
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return advices;

    }

    @Override
    protected void onPostExecute(List<Advice> advice) { // result es el resultado de doInBackground
        super.onPostExecute(advice);

//        firstFragment.binding.textviewName.setText(messages.get(0).getName());
//        firstFragment.binding.textviewContent.setText(messages.get(1).getCategory().getName());

    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate();
    }
}
