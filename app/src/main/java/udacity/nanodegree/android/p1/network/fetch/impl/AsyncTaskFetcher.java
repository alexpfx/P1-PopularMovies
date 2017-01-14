package udacity.nanodegree.android.p1.network.fetch.impl;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import udacity.nanodegree.android.p1.network.fetch.AbstractMovieFetcher;
import udacity.nanodegree.android.p1.network.fetch.MovieFetcher;
import udacity.nanodegree.android.p1.network.fetch.UriComposer;

/**
 * Created by alexandre on 12/01/2017.
 */

public class AsyncTaskFetcher extends AbstractMovieFetcher implements MovieFetcher {

    public AsyncTaskFetcher(Context context,
            UriComposer movieDbUriComposer,
            ResponseListener responseListener,
            @Nullable ErrorListener errorListener) {
        super(context, movieDbUriComposer, responseListener, errorListener);
    }

    @Override
    protected void doFetch(Uri uri) {
        backgroundTask.execute(uri);
    }

    AsyncTask<Uri, Void, String> backgroundTask = new AsyncTask<Uri, Void, String>() {
        @Override
        protected String doInBackground(Uri... params) {
            return fetchData(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            getResponseListener().onResponse(s);
        }


    };


    private String fetchData(Uri uri) {
        try {
            URL url = new URL(uri.toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            StringBuilder sb = new StringBuilder();

            try (InputStream inputStream = urlConnection.getInputStream()) {
                if (inputStream == null) {
                    return "";
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                if (sb.length() == 0) {
                    return "";
                }
            }

            return sb.toString();
        } catch (IOException e) {
            if (getErrorListener() != null) {
                getErrorListener().onError(e.getMessage(), null, e);
            } else {

            }
        }
        return null;
    }


}
