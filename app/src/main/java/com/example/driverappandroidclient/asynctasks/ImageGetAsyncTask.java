package com.example.driverappandroidclient.asynctasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.driverappandroidclient.FirstFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageGetAsyncTask extends AsyncTask<String, String, Bitmap> {

    public final FirstFragment firstFragment;

    public ImageGetAsyncTask(FirstFragment firstFragment) {
        this.firstFragment = firstFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected Bitmap doInBackground(String... params) {

        Bitmap bitmap = null;

        try {
            URL imageApiEndpoint = new URL("http://10.0.2.2:8080/images/"+params[0]+".jpg");
            HttpURLConnection myConnection =
                    (HttpURLConnection) imageApiEndpoint.openConnection();

            myConnection.setConnectTimeout(3000);

            if (myConnection.getResponseCode() == 200) {


                InputStream response = myConnection.getInputStream();
                if (bitmap != null) {
                    bitmap.recycle();
                }
                bitmap = BitmapFactory.decodeStream(response);
                myConnection.disconnect();


            } else {
                // Error handling code goes here
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        firstFragment.binding.imageView.setImageBitmap(bitmap);

    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate();
    }
}
