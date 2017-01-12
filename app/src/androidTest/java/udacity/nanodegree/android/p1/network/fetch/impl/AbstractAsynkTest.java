package udacity.nanodegree.android.p1.network.fetch.impl;

import android.os.ConditionVariable;
import android.util.Log;

import org.junit.After;
import org.junit.Before;

/**
 * Created by alexandre on 12/01/2017.
 */

public abstract class AbstractAsynkTest {

    private static final String TAG = "AbstractAsynkTest";
    private ConditionVariable mConditionVariable;

    public AbstractAsynkTest() {
        mConditionVariable = new ConditionVariable(false);
    }

    protected final void block() {
        Log.d(TAG, "block: ");
        mConditionVariable.block();
    }

    protected final void open() {
        Log.d(TAG, "open: ");
        mConditionVariable.open();
    }

    @After
    public void end() {
        Log.d(TAG, "end: ");
    }


}
