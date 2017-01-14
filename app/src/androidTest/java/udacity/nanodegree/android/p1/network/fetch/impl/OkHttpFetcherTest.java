package udacity.nanodegree.android.p1.network.fetch.impl;

import android.net.Uri;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import udacity.nanodegree.android.p1.MainActivity;
import udacity.nanodegree.android.p1.network.fetch.MovieFetcher;
import udacity.nanodegree.android.p1.network.fetch.UriComposer;

/**
 * Created by alexandre on 14/01/2017.
 */
/**
 * Created by alexandre on 11/01/2017.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class OkHttpFetcherTest extends AbstractAsynkTest{
    private static final String TAG = "VolleyFetcherTest";
    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    OkHttpFetcher mOkHttpFetcher;
    @Before
    public void setUp() throws Exception {
        mOkHttpFetcher = new OkHttpFetcher(mainActivityRule.getActivity(),
                new UriComposer() {
                    @Override
                    public Uri compose(Uri baseUrl) {
                        return null;
                    }
                }, new MovieFetcher.ResponseListener() {
            @Override
            public void onResponse(String data) {
                Log.d(TAG, "onResponse: "+data);
                open();
            }
        });

        block();
    }



    @After
    public void tearDown() throws Exception {

    }

}