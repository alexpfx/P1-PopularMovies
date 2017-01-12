package udacity.nanodegree.android.p1.network.fetch;

import android.support.annotation.Nullable;

/**
 * Created by alexandre on 11/01/2017.
 */

public interface MovieDbErrorListener {
    void onError (String msg, @Nullable Object info, Throwable e);
}
