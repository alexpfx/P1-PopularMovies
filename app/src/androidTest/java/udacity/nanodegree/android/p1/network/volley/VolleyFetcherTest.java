package udacity.nanodegree.android.p1.network.volley;

import android.net.Uri;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import udacity.nanodegree.android.p1.MainActivity;
import udacity.nanodegree.android.p1.network.fetch.MovieDbResponseListener;
import udacity.nanodegree.android.p1.network.fetch.MovieDbUriComposer;
import udacity.nanodegree.android.p1.network.fetch.impl.VolleyFetcher;

/**
 * Created by alexandre on 11/01/2017.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class VolleyFetcherTest {
    private static final String TAG = "VolleyFetcherTest";
    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void start() throws Throwable {
        mainActivityRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new VolleyFetcher(mainActivityRule.getActivity().getApplicationContext(),
                        new MovieDbUriComposer() {
                            @Override
                            public Uri compose(Uri baseUrl) {
                                return baseUrl.buildUpon().appendPath("455").build();
                            }
                        }, new MovieDbResponseListener() {
                    @Override
                    public void onResponse(String data) {
                        Assert.assertNotNull(data);
                    }
                }).startFetch();
            }
        });

    }

}