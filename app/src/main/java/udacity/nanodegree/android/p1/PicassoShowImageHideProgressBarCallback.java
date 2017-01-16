package udacity.nanodegree.android.p1;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;

/**
 * Created by alexandre on 16/01/2017.
 */

public class PicassoShowImageHideProgressBarCallback extends Callback.EmptyCallback {
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    public PicassoShowImageHideProgressBarCallback(ImageView imageView,
            ProgressBar progressBar) {
        mImageView = imageView;
        mProgressBar = progressBar;
    }

    @Override
    public void onSuccess() {
        mImageView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);

    }
}
