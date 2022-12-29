package com.example.driverappandroidclient.asynctasks;

import android.os.AsyncTask;
import android.util.JsonWriter;

import com.example.driverappandroidclient.FirstFragment;
import com.example.driverappandroidclient.converter.JsonRederToAdviceConverter;
import com.example.driverappandroidclient.model.Advice;
import com.example.driverappandroidclient.model.Rating;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SendAdviceRatingAsyncTask extends AsyncTask<String, String, String> {

    private final FirstFragment firstFragment;

    public SendAdviceRatingAsyncTask(FirstFragment firstFragment) {
        this.firstFragment = firstFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected String doInBackground(String... params) {

        Advice advice = null;
        String result = "";
//        URL apiEndpoint;
//        HttpURLConnection myConnection;


        try {
            final URL apiEndpoint = new URL("http://10.0.2.2:8080/addadvicerating");
            HttpURLConnection myConnection = (HttpURLConnection) apiEndpoint.openConnection();
            myConnection.setConnectTimeout(3000);
            myConnection.setRequestMethod("POST");
            myConnection.setRequestProperty("Content-Type", "application/json");
            myConnection.setRequestProperty("Accept", "application/json");
            myConnection.setDoOutput(true);
//            myConnection.setChunkedStreamingMode(0);

            try (OutputStream os = myConnection.getOutputStream()) {

                System.out.println("!!!!" + params[0] + " - " + (params[1]));

                Rating ratingToSend = new Rating(null,
                        Integer.valueOf(params[0]),
                        Long.valueOf(params[1]));
                JsonWriter writer =  new JsonRederToAdviceConverter().writeRating(
                                os,
                                ratingToSend);
                writer.close();
            }


//            try (BufferedReader br = new BufferedReader(
//                    new InputStreamReader(myConnection.getInputStream(), "utf-8"))) {
//                StringBuilder response = new StringBuilder();
//                String responseLine = null;
//                while ((responseLine = br.readLine()) != null) {
//                    response.append(responseLine.trim());
//                }
//                System.out.println("Resp.:" + response);
//            }


            System.out.println("Resp. Code: " + myConnection.getResponseCode());

            if (myConnection.getResponseCode() == 201) {
                result = "Dodano";
            } else if (myConnection.getResponseCode() == 406) {
                result = "Nie dodano - zła wartość";
            } else {
                    result = String.valueOf(myConnection.getResponseCode());
                result = "Nie dodano: "+result;
            }
            myConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        firstFragment.showInToast(result);


//        Bitmap bitmap = null;
//        if(advice != null)
//        {
//            firstFragment.binding.textviewName.setText(advice.getName());
//            firstFragment.binding.textviewContent.setText(advice.getAdviceText());
//            if(advice.getImage()!=null) {
//                new ImageGetAsyncTask(firstFragment).execute(advice.getImage().getLink());
//            }
//            firstFragment.binding.textviewRatingValue.setText(String.valueOf(advice.getRating()));
//        }
//        else
//        {
//            firstFragment.binding.textviewName.setText("Brak porady o takim numerze");
//            firstFragment.binding.textviewContent.setText("");
//            firstFragment.binding.imageView.setImageBitmap(bitmap);
//        }


    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate();
    }
}

