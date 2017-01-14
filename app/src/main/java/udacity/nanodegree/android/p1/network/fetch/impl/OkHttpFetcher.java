package udacity.nanodegree.android.p1.network.fetch.impl;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import udacity.nanodegree.android.p1.network.fetch.AbstractMovieDbFetcher;
import udacity.nanodegree.android.p1.network.fetch.MovieDbFetcher;
import udacity.nanodegree.android.p1.network.fetch.MovieDbResponseListener;
import udacity.nanodegree.android.p1.network.fetch.MovieDbUriComposer;

/**
 * Created by alexandre on 14/01/2017.
 */

public class OkHttpFetcher extends AbstractMovieDbFetcher implements MovieDbFetcher, Callback {
    private static final String TAG = "OkHttpFetcher";

    protected OkHttpFetcher(Context context,
            MovieDbUriComposer movieDbUriComposer,
            MovieDbResponseListener responseListener) {
        super(context, movieDbUriComposer, responseListener);
    }

    @Override
    protected void doFetch(Uri uri) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(uri.toString()).build();

        client.newCall(request).enqueue(this);

    }

    @Override
    public void onFailure(Call call, IOException e) {
        getErrorListener().onError("network error ", call, e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (!response.isSuccessful()) onFailure(call, new IOException(response.message()));

        Log.d(TAG, "onResponse: " + response.body().toString());

    }
}
