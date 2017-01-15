package udacity.nanodegree.android.p1.network.fetch.impl;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.test.filters.SmallTest;
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
import udacity.nanodegree.android.p1.util.AbstractAsynkTest;

/**
 * Created by alexandre on 11/01/2017.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class VolleyFetcherTest extends AbstractAsynkTest {
    private static final String TAG = "VolleyFetcherTest";
    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void start() throws Throwable {
        new VolleyFetcher(mainActivityRule.getActivity().getApplicationContext(),
                new UriComposer() {
                    @Override
                    public Uri compose(Uri baseUrl) {
                        return baseUrl.buildUpon().appendPath("550").build();
                    }
                }, new MovieFetcher.ResponseListener() {
            @Override
            public void onResponse(String data) {
                Assert.assertNotNull(data);
                Log.d(TAG, "onResponse: " + data);
                open();
            }
        }, new MovieFetcher.ErrorListener() {
            @Override
            public void onError(String msg, @Nullable Object info, Throwable e) {
                e.printStackTrace();
                open();

            }
        }).startFetch();

        block();
    }

}