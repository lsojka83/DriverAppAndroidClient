//package com.example.driverapp.asynctasks;
//
//import android.os.AsyncTask;
//
//import com.example.driverapp.FirstFragment;
//import com.example.driverapp.model.Message;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AdviceGetAsyncTask_example extends AsyncTask<String, String, List<Message>> {
//
//    private final FirstFragment firstFragment;
//
//    public AdviceGetAsyncTask_example(FirstFragment firstFragment) {
//        this.firstFragment = firstFragment;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//
//
//    }
//
//    @Override
//    protected List<Message> doInBackground(String... params) {
//
//        List<Message> advices = new ArrayList<>();
//
//        try {
//            URL apiEndpoint = new URL("http://10.0.2.2:8080/advices/");
//            HttpURLConnection myConnection =
//                    (HttpURLConnection) apiEndpoint.openConnection();
////                    myConnection.setRequestProperty("User-Agent", "abc");
////                    myConnection.setRequestProperty("Accept",
////                            "application/vnd.github.v3+json");
////                    myConnection.setRequestProperty("Contact-Me",
////                            "hathibelagal@example.com");
//
//
//            if (myConnection.getResponseCode() == 200) {
//                // Success
//                // Further processing here
//                InputStream responseBody = myConnection.getInputStream();
//                advices = firstFragment.readJsonStream(responseBody);
//
//                myConnection.disconnect();
//
//
//            } else {
//                // Error handling code goes here
//            }
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return advices;
//
//    }
//
//    @Override
//    protected void onPostExecute(List<Message> messages) {
//        super.onPostExecute(messages);
//
//        firstFragment.binding.textviewName.setText(messages.get(0).getName());
//        firstFragment.binding.textviewContent.setText(messages.get(1).getCategory().getName());
//        new ImageGetAsyncTask(firstFragment).execute("100");
//
//    }
//
//    @Override
//    protected void onProgressUpdate(String... values) {
//        super.onProgressUpdate();
//    }
//}
