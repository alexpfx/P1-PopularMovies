package udacity.nanodegree.android.p1.network.fetch;

import android.content.Context;
import android.net.Uri;

import udacity.nanodegree.android.p1.BuildConfig;
import udacity.nanodegree.android.p1.R;

public abstract class AbstractMovieFetcher implements MovieFetcher {
    public static final String API_KEY_PARAM = "api_key";
    private final ResponseListener mResponseListener;
    private Context mContext;
    private ErrorListener mErrorListener;


    protected AbstractMovieFetcher(Context context, ResponseListener responseListener,
            ErrorListener errorListener) {
        mContext = context;
        this.mResponseListener = responseListener;
        this.mErrorListener = errorListener;
    }

    @Override
    public void startFetch(UriComposer composer) {
        doFetch(composer.compose(Uri.parse(
                mContext.getString(R.string.tmdb_api_base_url))).buildUpon().appendQueryParameter(
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

    public ErrorListener getErrorListener() {
        return mErrorListener;
    }

    public void setErrorListener(
            ErrorListener errorListener) {
        mErrorListener = errorListener;
    }
}
