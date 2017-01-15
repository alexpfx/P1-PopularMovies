package udacity.nanodegree.android.p1.network.fetch;

import android.content.Context;
import android.net.Uri;

import udacity.nanodegree.android.p1.BuildConfig;
import udacity.nanodegree.android.p1.R;

/**
 * Created by alexandre on 11/01/2017.
 */

public abstract class AbstractMovieFetcher implements MovieFetcher {
    public static final String API_KEY_PARAM = "api_key";
    private UriComposer mMovieDbUriComposer;
    private Context mContext;
    private final ResponseListener mResponseListener;
    private ErrorListener mErrorListener;


    protected AbstractMovieFetcher(Context context,
            UriComposer movieDbUriComposer, ResponseListener responseListener,
            ErrorListener errorListener) {
        mMovieDbUriComposer = movieDbUriComposer;
        mContext = context;
        this.mResponseListener = responseListener;
        this.mErrorListener = errorListener;
    }

    @Override
    public void startFetch() {
        doFetch(mMovieDbUriComposer.compose(Uri.parse(mContext.getString(R.string.tmdb_api_base_url))).buildUpon().appendQueryParameter(
                API_KEY_PARAM,
                BuildConfig.MOVIE_DB_API_KEY).build());
    }

    protected abstract void doFetch(Uri uri);


    public Context getContext() {
        return mContext;
    }

    public ResponseListener getResponseListener() {
        return mResponseListener;
    }

    public void setErrorListener(
            ErrorListener errorListener) {
        mErrorListener = errorListener;
    }

    public ErrorListener getErrorListener() {
        return mErrorListener;
    }
}
