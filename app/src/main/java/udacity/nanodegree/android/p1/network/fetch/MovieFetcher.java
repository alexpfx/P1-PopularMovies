package udacity.nanodegree.android.p1.network.fetch;

/**
 * Created by alexandre on 11/01/2017.
 */

import android.support.annotation.Nullable;

/**
 * TODO: javadoc
 */
public interface MovieFetcher {
    void startFetch(UriComposer mMovieDbUriComposer);



    interface ResponseListener {
        void onResponse(String data);
    }

    interface ErrorListener {
        void onError(String msg, @Nullable Object info, Throwable e);
    }
}
