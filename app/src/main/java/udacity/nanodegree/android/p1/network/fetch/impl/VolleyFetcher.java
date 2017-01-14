package udacity.nanodegree.android.p1.network.fetch.impl;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import udacity.nanodegree.android.p1.network.fetch.AbstractMovieFetcher;
import udacity.nanodegree.android.p1.network.fetch.MovieFetcher;
import udacity.nanodegree.android.p1.network.fetch.UriComposer;

/**
 * Created by alexandre on 11/01/2017.
 */

public class VolleyFetcher extends AbstractMovieFetcher implements Response.ErrorListener,
        MovieFetcher,
        Response.Listener<JSONObject> {


    public VolleyFetcher(Context context,
            UriComposer movieDbUriComposer, ResponseListener responseListener) {
        super(context, movieDbUriComposer, responseListener);
    }

    public VolleyFetcher(Context context,
            UriComposer movieDbUriComposer,
            ResponseListener responseListener,
            @Nullable ErrorListener errorListener) {
        super(context, movieDbUriComposer, responseListener, errorListener);
    }

    @Override
    protected void doFetch(Uri uri) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                uri.toString(), null, this, this);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        if (getErrorListener() == null) {
            return;
        }
        getErrorListener().onError(error.getMessage(), error.networkResponse, error.getCause());
    }

    @Override
    public void onResponse(JSONObject response) {
        getResponseListener().onResponse(response.toString());
    }
}
