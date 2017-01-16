package udacity.nanodegree.android.p1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandre on 15/01/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private static final String TAG = "MoviesAdapter";
    private List<MovieViewModel> mData = new ArrayList<>();
    private Context mContext;
    private OnMovieSelected mOnMovieSelected;


    public MoviesAdapter(OnMovieSelected onMovieSelected) {
        mOnMovieSelected = onMovieSelected;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_view_item, parent, false);

        return new MoviesViewHolder(view, mOnMovieSelected);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        MovieViewModel urlPath = mData.get(position);
        Log.d(TAG, "onBindViewHolder: " + urlPath);
        holder.bind(urlPath);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(@NonNull List<MovieViewModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public interface OnMovieSelected {
        void onMovieSelected(int id);
    }

    static class MovieViewModel {
        private final int id;
        private final String path;

        public MovieViewModel(int id, String path) {
            this.id = id;
            this.path = path;
        }

        @Override
        public String toString() {
            return "MovieViewModel{" +
                    "id=" + id +
                    ", path='" + path + '\'' +
                    '}';
        }
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.poster_image)
        ImageView mImageView;

        @BindView(R.id.pb_progress_loading)
        ProgressBar mProgressBar;
        private OnMovieSelected mOnMovieSelected;

        public MoviesViewHolder(View itemView, OnMovieSelected onMovieSelected) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            mOnMovieSelected = onMovieSelected;
        }


        public void showImage() {
            mImageView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }


        public void bind(MovieViewModel model) {
            //TODO: placeholder e error
            String path = mContext.getString(R.string.tmdb_image_base_path, model.path);

            Picasso.with(mContext).load(path).into(mImageView,
                    new PicassoShowImageHideProgressBarCallback(mImageView, mProgressBar));
        }


        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            MovieViewModel movieViewModel = mData.get(adapterPosition);
            mOnMovieSelected.onMovieSelected(movieViewModel.id);
        }
    }

}
