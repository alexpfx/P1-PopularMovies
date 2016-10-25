package udacity.nanodegree.android.p1;

import android.net.Uri;

/**
 * Created by alexandre on 23/10/2016.
 */
public class FetchTopMovies implements FetchMovies.FetchRules {
    @Override
    public Uri composeUrl(Uri baseUrl) {
        return baseUrl.buildUpon().appendPath("top_rated").build();
    }
}
