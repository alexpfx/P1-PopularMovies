package udacity.nanodegree.android.p1.network.fetch;

/**
 * Created by alexandre on 11/01/2017.
 */

import android.support.annotation.Nullable;

/**
 *
 */
public interface MovieFetcher {
    void startFetch();


    /**
     * Created by alexandre on 11/01/2017.
     */

    interface ResponseListener {
        void onResponse(String data);
    }

    /**
     * Created by alexandre on 11/01/2017.
     */

    interface ErrorListener {
        void onError(String msg, @Nullable Object info, Throwable e);
    }
}
