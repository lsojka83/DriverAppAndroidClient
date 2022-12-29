package com.example.driverappandroidclient.asynctasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.driverappandroidclient.FirstFragment;
import com.example.driverappandroidclient.converter.JsonRederToAdviceConverter;
import com.example.driverappandroidclient.model.Advice;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetAdviceAsyncTask extends AsyncTask<String, String, Advice> {

    private final FirstFragment firstFragment;

    public GetAdviceAsyncTask(FirstFragment firstFragment) {
        this.firstFragment = firstFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected Advice doInBackground(String... params) {

        Advice advice = null;

        try {
            URL apiEndpoint = new URL("http://10.0.2.2:8080/advices/"+params[0]);
            HttpURLConnection myConnection =
                    (HttpURLConnection) apiEndpoint.openConnection();
            myConnection.setConnectTimeout(3000);



            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here
                InputStream responseBody = myConnection.getInputStream();
//                InputStreamReader responseBodyReader =
//                        new InputStreamReader(responseBody, "UTF-8");
                JsonReader reader = new JsonReader(new InputStreamReader(responseBody, "UTF-8"));

                advice = new JsonRederToAdviceConverter().readMessage(reader);
                myConnection.disconnect();


            } else {
                // Error handling code goes here
                System.out.println(myConnection.getResponseCode());
                myConnection.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return advice;

    }

    @Override
    protected void onPostExecute(Advice advice) {
        super.onPostExecute(advice);

        firstFragment.setCurrentAdvice(advice);

        Bitmap bitmap = null;
        if(advice != null)
        {
            firstFragment.binding.textviewName.setText(advice.getName());
            firstFragment.binding.textviewContent.setText(advice.getAdviceText());
            if(advice.getImage()!=null) {
                new ImageGetAsyncTask(firstFragment).execute(advice.getImage().getLink());
            }
            if(advice.getRating()==-1d)
            {
                firstFragment.binding.textviewRatingValue.setText("-");
            }
            else
            {
                firstFragment.binding.textviewRatingValue.setText(String.valueOf(advice.getRating()));
            }
        }
        else
        {
            firstFragment.binding.textviewName.setText("Brak porady o takim numerze");
            firstFragment.binding.textviewContent.setText("");
            firstFragment.binding.imageView.setImageBitmap(bitmap);
        }


    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate();
    }
}

