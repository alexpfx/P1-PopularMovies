package udacity.nanodegree.android.p1.network.fetch;

import android.content.Context;
import android.net.Uri;

import io.reactivex.Observable;
import udacity.nanodegree.android.p1.BuildConfig;
import udacity.nanodegree.android.p1.R;

/**
 * Created by alexandre on 08/05/2017.
 */

public abstract class RxMovieFetcherImpl<T> implements RxMovieFetcher<Observable<T>> {
    public static final String API_KEY_PARAM = "api_key";
    private Context mContext;
    private MovieFetcher.ErrorListener mErrorListener;

    @Override
    public Observable<T> startFetch(UriComposer composer) {
        String baseUrl = mContext.getString(R.string.tmdb_api_base_url);
        return doFetch(composer.compose(Uri.parse(
                baseUrl)).buildUpon().appendQueryParameter(
                API_KEY_PARAM,
                BuildConfig.MOVIE_DB_API_KEY).build(), baseUrl);
    }

    protected abstract Observable<T> doFetch(Uri uri, String baseUrl);
}
