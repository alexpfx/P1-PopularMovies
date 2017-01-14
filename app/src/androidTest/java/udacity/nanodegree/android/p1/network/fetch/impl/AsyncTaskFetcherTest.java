package udacity.nanodegree.android.p1.network.fetch.impl;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import udacity.nanodegree.android.p1.MainActivity;
import udacity.nanodegree.android.p1.network.fetch.MovieFetcher;
import udacity.nanodegree.android.p1.network.fetch.UriComposer;

/**
 * Created by alexandre on 12/01/2017.
 */

@RunWith(AndroidJUnit4.class)
public class AsyncTaskFetcherTest extends AbstractAsynkTest{
    private static final String TAG = "AsyncTaskFetcherTest";
    @Rule
    public final ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void doFetch() throws Throwable {

        new AsyncTaskFetcher(mainActivityRule.getActivity(), new UriComposer() {
            @Override
            public Uri compose(Uri baseUrl) {
                return baseUrl.buildUpon().appendPath("455").build();
            }
        }, new MovieFetcher.ResponseListener() {
            @Override
            public void onResponse(String data) {
                Log.d(TAG, "onResponse: " + data);
                Assert.assertNotNull(data);
                open();

            }

        }, new MovieFetcher.ErrorListener() {
            @Override
            public void onError(String msg, @Nullable Object info, Throwable e) {

            }
        }).startFetch();
        block();
    }



}