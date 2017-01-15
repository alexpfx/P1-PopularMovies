package udacity.nanodegree.android.p1.network.fetch.impl;

import android.content.Context;
import android.net.Uri;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import udacity.nanodegree.android.p1.R;
import udacity.nanodegree.android.p1.network.fetch.AbstractMovieFetcher;
import udacity.nanodegree.android.p1.network.fetch.MovieFetcher;
import udacity.nanodegree.android.p1.network.fetch.UriComposer;

/**
 * Created by alexandre on 14/01/2017.
 */

public class OkHttpFetcher extends AbstractMovieFetcher implements MovieFetcher, Callback {
    private static final String TAG = "OkHttpFetcher";


    public OkHttpFetcher(Context context,
            UriComposer movieDbUriComposer,
            ResponseListener responseListener,
            ErrorListener errorListener) {
        super(context, movieDbUriComposer, responseListener, errorListener);
    }

    @Override
    protected void doFetch(Uri uri) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(uri.toString()).build();

        client.newCall(request).enqueue(this);

    }

    @Override
    public void onFailure(Call call, IOException e) {
        getErrorListener().onError(getContext().getString(R.string.error_not_connected), call, e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (!response.isSuccessful()) onFailure(call, new IOException(response.message()));

        getResponseListener().onResponse(response.body().string());

    }
}
