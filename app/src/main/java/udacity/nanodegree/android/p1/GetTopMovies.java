package udacity.nanodegree.android.p1;

import android.net.Uri;

import udacity.nanodegree.android.p1.network.fetch.UriComposer;

/**
 * Created by alexandre on 23/10/2016.
 */
public class GetTopMovies implements UriComposer {
    @Override
    public Uri compose(Uri baseUrl) {
        return baseUrl.buildUpon().appendPath("top_rated").build();
    }
}
