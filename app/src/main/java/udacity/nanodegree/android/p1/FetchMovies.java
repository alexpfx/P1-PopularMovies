package udacity.nanodegree.android.p1;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alexandre on 22/10/2016.
 * Generic Task responsible for fetch data from moviedb.
 */
public class FetchMovies extends AsyncTask<String, Void, String> {
    private static final String URL_BASE = "https://api.themoviedb.org/3/movie/?";

    public FetchMovies(FetchRules fetchRules, Callback callback) {
        this.fetchRules = fetchRules;
        this.callback = callback;

    }

    private FetchRules fetchRules;
    private Callback callback;


    @Override
    protected String doInBackground(String... params) {
        Uri clientUrl = fetchRules.composeUrl(Uri.parse(URL_BASE)).buildUpon().appendQueryParameter("api_key", BuildConfig.MOVIE_DB_API_KEY).build();
        return fetchData(clientUrl);
    }

    public interface FetchRules {
        Uri composeUrl(Uri baseUrl);
    }

    @Override
    protected void onPostExecute(String data) {
        callback.onReceived(data);
    }

    public interface Callback {
        void onReceived(String data);
    }

    private String fetchData(Uri uri) {
        try {
            URL url = new URL(uri.toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return "";
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            if (sb.length() == 0) {
                return "";
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
