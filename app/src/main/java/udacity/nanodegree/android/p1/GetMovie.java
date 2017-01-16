package udacity.nanodegree.android.p1;

import android.net.Uri;

import udacity.nanodegree.android.p1.network.fetch.UriComposer;

/**
 * Created by alexandre on 15/01/2017.
 */

public class GetMovie implements UriComposer {

    private String id;

    public GetMovie(String id) {
        this.id = id;
    }

    @Override
    public Uri compose(Uri baseUrl) {
        return baseUrl.buildUpon().appendPath(id).build();
    }
}
