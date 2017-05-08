package udacity.nanodegree.android.p1.network.fetch.impl;

import android.content.Context;
import android.net.Uri;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;
import udacity.nanodegree.android.p1.R;
import udacity.nanodegree.android.p1.network.fetch.AbstractMovieFetcher;

/**
 * Created by alexandre on 08/05/2017.
 */

public class RetrofitFetcher extends AbstractMovieFetcher implements Callback<String> {
    private static final String TAG = "RetrofitFetcher";
    public RetrofitFetcher(Context context, ResponseListener responseListener, ErrorListener errorListener) {
        super(context, responseListener, errorListener);
    }

    @Override
    protected void doFetch(Uri uri) {
        //baseUrl is obligatory, but ignored in this case.
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(getContext().getString(R.string.tmdb_api_base_url)).addConverterFactory(
                ScalarsConverterFactory.create()).build();
        Query query = retrofit.create(Query.class);
        Call<String> call = query.queryMovieDatabase(uri.toString());
        call.enqueue(this);

    }



    @Override
    public void onResponse(Call<String> call, Response<String> response) {

        getResponseListener().onResponse(response.body());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        getErrorListener().onError(t.getMessage(), null, t);
    }

    public interface Query {

        @GET
        Call<String> queryMovieDatabase(@Url String url);
    }
}
