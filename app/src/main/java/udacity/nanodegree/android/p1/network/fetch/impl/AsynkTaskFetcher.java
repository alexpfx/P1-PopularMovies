package udacity.nanodegree.android.p1.network.fetch.impl;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import udacity.nanodegree.android.p1.network.fetch.AbstractMovieDbFetcher;
import udacity.nanodegree.android.p1.network.fetch.MovieDbFetcher;
import udacity.nanodegree.android.p1.network.fetch.MovieDbResponseListener;
import udacity.nanodegree.android.p1.network.fetch.MovieDbUriComposer;

/**
 * Created by alexandre on 12/01/2017.
 */

public class AsynkTaskFetcher extends AbstractMovieDbFetcher implements MovieDbFetcher {


    protected AsynkTaskFetcher(Context context,
            MovieDbUriComposer movieDbUriComposer,
            MovieDbResponseListener responseListener) {
        super(context, movieDbUriComposer, responseListener);
    }

    @Override
    public void start(Uri uri) {

    }

}
