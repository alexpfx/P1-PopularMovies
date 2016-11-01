package udacity.nanodegree.android.p1;

import android.net.Uri;
import android.util.Log;

/**
 * Created by alexandre on 22/10/2016.
 */

public class GetPopularMovies implements FetchMovies.FetchRules {

    private static final String TAG = "GetPopularMovies";

    @Override
    public Uri composeUrl(Uri baseUrl) {
        Log.d(TAG, "composeUrl: " + baseUrl);
        return baseUrl.buildUpon().appendPath("popular").build();
    }
}
