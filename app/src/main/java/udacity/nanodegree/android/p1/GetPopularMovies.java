package udacity.nanodegree.android.p1;

import android.net.Uri;

import udacity.nanodegree.android.p1.network.fetch.UriComposer;

/**
 * Created by alexandre on 22/10/2016.
 */
public class GetPopularMovies implements UriComposer {

    public static final String POPULAR = "popular";

    @Override
    public Uri compose(Uri baseUrl) {
        return baseUrl.buildUpon().appendPath(POPULAR).build();
    }
}
