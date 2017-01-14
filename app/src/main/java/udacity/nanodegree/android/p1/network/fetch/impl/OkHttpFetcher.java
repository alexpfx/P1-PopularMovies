package udacity.nanodegree.android.p1.network.fetch.impl;

import android.content.Context;
import android.net.Uri;

import udacity.nanodegree.android.p1.network.fetch.AbstractMovieDbFetcher;
import udacity.nanodegree.android.p1.network.fetch.MovieDbFetcher;
import udacity.nanodegree.android.p1.network.fetch.MovieDbResponseListener;
import udacity.nanodegree.android.p1.network.fetch.MovieDbUriComposer;

/**
 * Created by alexandre on 14/01/2017.
 */

public class OkHttpFetcher extends AbstractMovieDbFetcher implements MovieDbFetcher {
    protected OkHttpFetcher(Context context,
            MovieDbUriComposer movieDbUriComposer,
            MovieDbResponseListener responseListener) {
        super(context, movieDbUriComposer, responseListener);
    }

    @Override
    protected void doFetch(Uri uri) {

    }
}
