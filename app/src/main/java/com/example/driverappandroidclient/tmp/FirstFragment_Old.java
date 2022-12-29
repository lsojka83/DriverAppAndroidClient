//package com.example.driverapp.tmp;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.JsonReader;
//import android.util.JsonToken;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//
//import com.example.driverapp.databinding.FragmentFirstBinding;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FirstFragment_Old extends Fragment {
//
//    private FragmentFirstBinding binding;
//
//
//    @Override
//    public View onCreateView(
//            LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState
//    ) {
//
//        binding = FragmentFirstBinding.inflate(inflater, container, false);
//        return binding.getRoot();
//
//    }
//
//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//
//        super.onViewCreated(view, savedInstanceState);
//
//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                binding.textviewFirst.setText("test");
//
//                new MyAsyncTask().execute("100");
//
////                MyAsyncTask runner = new MyAsyncTask();
////                runner.execute("1");
//
//
////                NavHostFragment.findNavController(FirstFragment.this)
////                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//
//
//    private class MyAsyncTask extends AsyncTask<String, String, List<String>> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//
//        }
//
//        @Override
//        protected List<String> doInBackground(String... params) {
//
//            List<String> values = new ArrayList<>();
//
//            try {
//                URL apiEndpoint = new URL("http://10.0.2.2:8080/advices/");
//                HttpURLConnection myConnection =
//                        (HttpURLConnection) apiEndpoint.openConnection();
////                    myConnection.setRequestProperty("User-Agent", "abc");
////                    myConnection.setRequestProperty("Accept",
////                            "application/vnd.github.v3+json");
////                    myConnection.setRequestProperty("Contact-Me",
////                            "hathibelagal@example.com");
//
//
//                if (myConnection.getResponseCode() == 200) {
//                    // Success
//                    // Further processing here
//                    InputStream responseBody = myConnection.getInputStream();
//                    InputStreamReader responseBodyReader =
//                            new InputStreamReader(responseBody, "UTF-8");
//
////                    JsonReader jsonReaderList =  new JsonReader(new InputStreamReader(responseBody, "UTF-8"));
////
////                    while (jsonReaderList.hasNext())
////                    {
////                        System.out.println("!!!! has next");
////
////                    }
////                    jsonReaderList.close();
//
//
////                    JSONArray jsonArray = new JSONArray(responseBody);
////                    System.out.println(jsonArray.get(0));
//
//                    List<Message> advices = readJsonStream(responseBody);
//
//                    System.out.println(advices.get(0).adviceText);
//
//
//
//
//
////                    BufferedReader bufferedReader = new BufferedReader(responseBodyReader);
////                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
////                        List<String> list = bufferedReader.lines().collect(Collectors.toList());
////                        JSONArray array = new JSONArray();
////
////                        for (String s : list) {
//////                            array.put(new JSONObject(s));
////                            System.out.println(s);
////                        }
////                        bufferedReader.close();
//////                        System.out.println(array);
////                    }
////
//////                    System.out.println(responseBodyReader.);
//
//
//
//
////                    List<JSONObject> jsonObjList = new ArrayList<>();
////                    int n = 1;
////                    while (jsonReader.hasNext())
////                    {
////                        System.out.println(n);
////                        n++;
////                    }
////                    jsonReader.endArray();
//
//
//                    JsonReader jsonReader = new JsonReader(responseBodyReader);
//                    jsonReader.beginArray();
//
//                    jsonReader.beginObject(); // Start processing the JSON object
//                    while (jsonReader.hasNext()) { // Loop through all keys
//                        String key = jsonReader.nextName(); // Fetch the next key
//                        if (key.equals("name")) { // Check if desired key
//                            // Fetch the value as a String
//                            values.add(jsonReader.nextString());
//                            // Do something with the value
//                            // ...
//                            break; // Break out of the loop
//                        }
//                        else {
//                            jsonReader.skipValue(); // Skip values of other keys
//                        }
//                    }
//
//                    while (jsonReader.hasNext()) { // Loop through all keys
//                        String key = jsonReader.nextName(); // Fetch the next key
//                        if (key.equals("adviceText")) { // Check if desired key
//                            // Fetch the value as a String
//                            values.add(jsonReader.nextString());
//                            // Do something with the value
//                            // ...
//                            break; // Break out of the loop
//                        }
//                        else {
//                            jsonReader.skipValue(); // Skip values of other keys
//                        }
//                    }
//
////                    jsonReader.endObject();
//
//                    jsonReader.close();
//                    myConnection.disconnect();
//
//
//                } else {
//                    // Error handling code goes here
//                }
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            for(String s : values)
//            {
//                System.out.println(s);
//            }
//
//            return values;
//
//        }
//
//        @Override
//        protected void onPostExecute(List<String> results) { // result es el resultado de doInBackground
//            super.onPostExecute(results);
//
////            binding.textviewName.setText(results.get(0));
////            binding.textviewContent.setText(results.get(1));
//
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate();
//        }
//    }
//
//    public List<Message> readJsonStream(InputStream in) throws IOException {
//        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
//        try {
//            return readMessagesArray(reader);
//        } finally {
//            reader.close();
//        }
//    }
//
//    public List<Message> readMessagesArray(JsonReader reader) throws IOException {
//        List<Message> messages = new ArrayList<>();
//
//        reader.beginArray();
//        while (reader.hasNext()) {
//            messages.add(readMessage(reader));
//        }
//        reader.endArray();
//        return messages;
//    }
//
//    public Message readMessage(JsonReader reader) throws IOException {
//        long id = -1;
//        double rating = -1d;
//        String name = null;
//        String adviceText = null;
//        Category category = null;
//        Image image = null;
//
//        reader.beginObject();
//        while (reader.hasNext()) {
//            String keyName = reader.nextName();
//            if (keyName.equals("id")) {
//                id = reader.nextLong();
//            } else if (keyName.equals("name")) {
//                name = reader.nextString(); }
//            else if (keyName.equals("adviceText")) {
//                adviceText = reader.nextString();
//            } else if (keyName.equals("category")) {
//                category = readCategory(reader);
//            } else if (keyName.equals("image")) {
//                image = readImage(reader);
//            } else if (keyName.equals("movie")) {
//                reader.skipValue();
//            } else if (keyName.equals("quizQuestions")) {
//                reader.skipValue();
//            } else if (keyName.equals("rating") && reader.peek() != JsonToken.NULL) {
//                 rating = reader.nextDouble();
//            } else {
//                reader.skipValue();
//            }
//        }
//        reader.endObject();
//        return new Message( id,  rating,  name,  adviceText,  category,  image);
//    }
//
//    public List<Double> readDoublesArray(JsonReader reader) throws IOException {
//        List<Double> doubles = new ArrayList<Double>();
//
//        reader.beginArray();
//        while (reader.hasNext()) {
//            doubles.add(reader.nextDouble());
//        }
//        reader.endArray();
//        return doubles;
//    }
//
//    public Category readCategory(JsonReader reader) throws IOException {
//        String name = null;
//        long id = -1;
//
//        reader.beginObject();
//        while (reader.hasNext()) {
//            String keyName = reader.nextName();
//            if (keyName.equals("id")) {
//                id = reader.nextLong();
//            }else if(keyName.equals("name")) {
//                name = reader.nextString();
//            } else {
//                reader.skipValue();
//            }
//        }
//        reader.endObject();
//        return new Category(name, id);
//    }
//
//    public Image readImage(JsonReader reader) throws IOException {
//        String name = null;
//        String link = null;
//        long id = -1;
//
//        reader.beginObject();
//        while (reader.hasNext()) {
//            String keyName = reader.nextName();
//            if (keyName.equals("id")) {
//                id = reader.nextLong();
//            }else if(keyName.equals("name")) {
//                name = reader.nextString();
//            }else if(keyName.equals("link")) {
//                link = reader.nextString();
//            } else {
//                reader.skipValue();
//            }
//        }
//        reader.endObject();
//        return new Image(link, name, id);
//    }
//
//    private class Category
//    {
//        String name = "";
//        long id = -1;
//
//        public Category(String name, long id) {
//            this.name = name;
//            this.id = id;
//        }
//    }
//
//    private class Image
//    {
//        String name = "";
//        String link = "";
//        long id = -1;
//
//        public Image(String name, String link, long id) {
//            this.name = name;
//            this.link = link;
//            this.id = id;
//        }
//    }
//
//
//    private class Message {
//        long id = -1;
//        double rating = -1d;
//        String name = null;
//        String adviceText = null;
//        Category category = null;
//        Image image = null;
//
//        public Message(long id, double rating, String name, String adviceText, Category category, Image image) {
//            this.id = id;
//            this.rating = rating;
//            this.name = name;
//            this.adviceText = adviceText;
//            this.category = category;
//            this.image = image;
//        }
//    }
//}