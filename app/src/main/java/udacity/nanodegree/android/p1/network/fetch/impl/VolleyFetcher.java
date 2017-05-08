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

import udacity.nanodegree.android.p1.R;
import udacity.nanodegree.android.p1.network.fetch.AbstractMovieFetcher;
import udacity.nanodegree.android.p1.network.fetch.MovieFetcher;

/**
 * MovieFetcher implementation using VolleyFetcher for Network layer.
 */
public class VolleyFetcher extends AbstractMovieFetcher implements Response.ErrorListener,
        MovieFetcher,
        Response.Listener<JSONObject> {


    public VolleyFetcher(Context context,
            ResponseListener responseListener,
            @Nullable ErrorListener errorListener) {
        super(context, responseListener, errorListener);



    }

    @Override
    protected void doFetch(Uri uri) {



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                uri.toString(), null, this, this);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError e) {
        getErrorListener().onError(getContext().getString(R.string.error_not_connected), null, e);
    }

    @Override
    public void onResponse(JSONObject response) {
        getResponseListener().onResponse(response.toString());
    }
}
