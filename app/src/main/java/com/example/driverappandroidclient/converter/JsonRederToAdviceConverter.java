package com.example.driverappandroidclient.converter;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;

import com.example.driverappandroidclient.model.Advice;
import com.example.driverappandroidclient.model.Category;
import com.example.driverappandroidclient.model.Image;
import com.example.driverappandroidclient.model.Rating;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class JsonRederToAdviceConverter {
    public List<Advice> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readMessagesArray(reader);
        } finally {
            reader.close();
        }
    }

    public List<Advice> readMessagesArray(JsonReader reader) throws IOException {
        List<Advice> advice = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            advice.add(readMessage(reader));
        }
        reader.endArray();
        return advice;
    }

    public Advice readMessage(JsonReader reader) throws IOException {
        long id = -1;
        double rating = -1d;
        long ratingsCount = 0l;
        String name = null;
        String adviceText = null;
        Category category = null;
        Image image = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String keyName = reader.nextName();
            if (keyName.equals("id")) {
                id = reader.nextLong();
            } else if (keyName.equals("name")) {
                name = reader.nextString();
            } else if (keyName.equals("adviceText")) {
                adviceText = reader.nextString();
            } else if (keyName.equals("category")) {
                category = readCategory(reader);
            } else if (keyName.equals("image") && reader.peek() != JsonToken.NULL) {
                image = readImage(reader);
            } else if (keyName.equals("movie")) {
                reader.skipValue();
            } else if (keyName.equals("quizQuestions")) {
                reader.skipValue();
            } else if (keyName.equals("rating") && reader.peek() != JsonToken.NULL) {
                rating = reader.nextDouble();
            } else if (keyName.equals("ratingsCount") && reader.peek() != JsonToken.NULL) {
                ratingsCount = reader.nextLong();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Advice(id, rating, ratingsCount, name, adviceText, category, image);
    }

    public List<Double> readDoublesArray(JsonReader reader) throws IOException {
        List<Double> doubles = new ArrayList<Double>();

        reader.beginArray();
        while (reader.hasNext()) {
            doubles.add(reader.nextDouble());
        }
        reader.endArray();
        return doubles;
    }

    public Category readCategory(JsonReader reader) throws IOException {
        String name = null;
        long id = -1;

        reader.beginObject();
        while (reader.hasNext()) {
            String keyName = reader.nextName();
            if (keyName.equals("id")) {
                id = reader.nextLong();
            } else if (keyName.equals("name")) {
                name = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Category(name, id);
    }

    public Image readImage(JsonReader reader) throws IOException {
        String name = null;
        String link = null;
        long id = -1;

        reader.beginObject();
        while (reader.hasNext()) {
            String keyName = reader.nextName();
            if (keyName.equals("id")) {
                id = reader.nextLong();
            } else if (keyName.equals("name")) {
                name = reader.nextString();
            } else if (keyName.equals("link")) {
                link = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Image(name, link, id);
    }


//   public void writeJsonStream(OutputStream out, List<Message> messages) throws IOException {
//      JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
//      writer.setIndent("  ");
//      writeMessagesArray(writer, messages);
//      writer.close();
//   }


    public JsonWriter writeRating(OutputStream out, Rating rating) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.beginObject();
        writer.name("id").nullValue();
        writer.name("value").value(rating.getValue());
        writer.name("adviceId").value(rating.getAdviceId());
        writer.endObject();

        return writer;
    }
}
