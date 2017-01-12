package udacity.nanodegree.android.p1.network.fetch;

import android.content.Context;
import android.net.Uri;

import udacity.nanodegree.android.p1.BuildConfig;

/**
 * Created by alexandre on 11/01/2017.
 */

public abstract class AbstractMovieDbFetcher implements MovieDbFetcher {
    public static final String API_KEY_PARAM = "api_key";
    private MovieDbUriComposer mMovieDbUriComposer;
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/?";
    private Context mContext;
    private final MovieDbResponseListener mResponseListener;
    private MovieDbErrorListener mErrorListener;

    protected AbstractMovieDbFetcher(Context context,
            MovieDbUriComposer movieDbUriComposer, MovieDbResponseListener responseListener) {
        mMovieDbUriComposer = movieDbUriComposer;
        mContext = context;
        this.mResponseListener = responseListener;
    }

    @Override
    public void startFetch() {
        start(mMovieDbUriComposer.compose(Uri.parse(BASE_URL)).buildUpon().appendQueryParameter(
                API_KEY_PARAM,
                BuildConfig.MOVIE_DB_API_KEY).build());
    }

    protected abstract void start(Uri uri);


    public Context getContext() {
        return mContext;
    }

    public MovieDbResponseListener getResponseListener() {
        return mResponseListener;
    }

    public void setErrorListener(
            MovieDbErrorListener errorListener) {
        mErrorListener = errorListener;
    }

    public MovieDbErrorListener getErrorListener() {
        return mErrorListener;
    }
}
