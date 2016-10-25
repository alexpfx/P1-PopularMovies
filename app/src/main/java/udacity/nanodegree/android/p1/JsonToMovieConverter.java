package udacity.nanodegree.android.p1;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import udacity.nanodegree.android.p1.domain.Page;

/**
 * Created by alexandre on 24/10/2016.
 */

public class JsonToMovieConverter {
    private String jsonString;
    private static final String TAG = "JsonToMovieConverter";

    public JsonToMovieConverter(String jsonString) {
        this.jsonString = jsonString;
    }

    public Page generate() {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Page.class);
    }

}
