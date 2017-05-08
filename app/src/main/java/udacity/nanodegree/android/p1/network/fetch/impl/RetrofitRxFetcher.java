package udacity.nanodegree.android.p1.network.fetch.impl;

import android.content.Context;
import android.net.Uri;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;
import udacity.nanodegree.android.p1.network.fetch.AbstractMovieFetcher;
import udacity.nanodegree.android.p1.network.fetch.AbstractRxMovieFetcher;
import udacity.nanodegree.android.p1.network.fetch.RxMovieFetcher;

/**
 * Created by alexandre on 08/05/2017.
 */

public class RetrofitRxFetcher extends AbstractRxMovieFetcher <String> implements RxMovieFetcher<Observable<String>>{


    public RetrofitRxFetcher(Context context) {
        super(context);
    }

    @Override
    protected Observable<String> doFetch(Uri uri, String baseUrl) {
        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(
                Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
                ScalarsConverterFactory.create()).addCallAdapterFactory(rxAdapter).build();

        Query query = retrofit.create(Query.class);
        Observable<String> result = query.queryMovieDatabase(uri.toString());

        return result;
    }

    public interface Query {

        @GET
        Observable<String> queryMovieDatabase(@Url String url);
    }


}
