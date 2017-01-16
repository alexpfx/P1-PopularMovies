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

import udacity.nanodegree.android.p1.R;
import udacity.nanodegree.android.p1.network.fetch.AbstractMovieFetcher;
import udacity.nanodegree.android.p1.network.fetch.MovieFetcher;

/**
 * Created by alexandre on 12/01/2017.
 */

public class AsyncTaskFetcher extends AbstractMovieFetcher implements MovieFetcher {

    public static final String GET = "GET";

    public AsyncTaskFetcher(Context context,
            ResponseListener responseListener,
            @Nullable ErrorListener errorListener) {
        super(context, responseListener, errorListener);
    }

    @Override
    protected void doFetch(Uri uri) {
        new BackgroundAsyncTask().execute(uri);
    }

    private String fetchData(Uri uri) {
        try {
            URL url = new URL(uri.toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(GET);
            urlConnection.connect();
            StringBuilder sb = new StringBuilder();

            try (InputStream inputStream = urlConnection.getInputStream()) {
                if (inputStream == null) {
                    return "";
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line).append(System.lineSeparator());
                }
                if (sb.length() == 0) {
                    return "";
                }
            }

            return sb.toString();
        } catch (IOException e) {
            getErrorListener().onError(getContext().getString(R.string.error_not_connected), null,
                    e);
        }
        return null;
    }

    class BackgroundAsyncTask extends AsyncTask<Uri, Void, String> {
        @Override
        protected String doInBackground(Uri... params) {
            return fetchData(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            getResponseListener().onResponse(s);
        }


    }


}
