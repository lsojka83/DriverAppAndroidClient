package com.example.driverappandroidclient.asynctasks;

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

public class GetAllAdvicesAsyncTask extends AsyncTask<String, String, List<Advice>> {

    private final FirstFragment firstFragment;

    public GetAllAdvicesAsyncTask(FirstFragment firstFragment) {
        this.firstFragment = firstFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        firstFragment.binding.textviewName.setText("");


    }

    @Override
    protected List<Advice> doInBackground(String... params) {

        List<Advice> advices = new ArrayList<>();

        try {
            URL apiEndpoint = new URL("http://10.0.2.2:8080/advices/");
            HttpURLConnection myConnection =
                    (HttpURLConnection) apiEndpoint.openConnection();
            myConnection.setConnectTimeout(3000);

            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here
                InputStream responseBody = myConnection.getInputStream();
                advices = new JsonRederToAdviceConverter().readJsonStream(responseBody);

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
    protected void onPostExecute(List<Advice> advices) {
        super.onPostExecute(advices);
        if(advices.size()>0) {
            firstFragment.binding.textViewAdvicesCount.setText(advices.size() + "");
        }
        else {
            firstFragment.binding.textviewName.setText("Nie mogę pobrać porad");
        }

    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate();
    }
}
