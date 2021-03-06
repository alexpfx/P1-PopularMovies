package udacity.nanodegree.android.p1;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.nanodegree.android.p1.network.dto.Page;
import udacity.nanodegree.android.p1.network.dto.Result;
import udacity.nanodegree.android.p1.network.fetch.MovieFetcher;
import udacity.nanodegree.android.p1.network.fetch.UriComposer;
import udacity.nanodegree.android.p1.network.fetch.impl.OkHttpFetcher;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements
        MoviesAdapter.OnMovieSelected, MovieFetcher.ErrorListener, MovieFetcher.ResponseListener {
    private static final String TAG = "MoviesFragment";
    private static final int SPAN_COUNT = 2;

    @BindView(R.id.rv_movie_list)
    RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;
    private MovieFetcher mFetcher;

    public MoviesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();

//        mFetcher = new VolleyFetcher(getContext(), this, this);
        mFetcher = new OkHttpFetcher(getContext(), this, this);


        fetchMovies(new GetPopularMovies());
        getActivity().setTitle(getString(R.string.most_popular));


        return view;
    }

    private void initRecyclerView() {
        mAdapter = new MoviesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getContext(),
                SPAN_COUNT);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }

    private void fetchMovies(UriComposer composer) {
        mFetcher.startFetch(composer);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        item.setChecked(!item.isChecked());
        switch (id) {
            case R.id.action_order_popular:
                fetchMovies(new GetPopularMovies());
                break;
            case R.id.action_order_top:
                fetchMovies(new GetTopMovies());
                break;
        }
        getActivity().setTitle(item.getTitle());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.movies_fragment_menu, menu);
    }

    @Override
    public void onMovieSelected(int id) {
        Intent intent = new Intent(getActivity(), DetailActivity.class).putExtra(Intent.EXTRA_TEXT,
                String.valueOf(id));
        startActivity(intent);
    }

    @Override
    public void onError(String msg, @Nullable Object info, Throwable e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        e.printStackTrace();

    }

    @Override
    public void onResponse(String jsonData) {
        Gson gson = new Gson();
        Page page = gson.fromJson(jsonData, Page.class);

        if (page == null) {
            Toast.makeText(getContext(), R.string.error_not_connected, Toast.LENGTH_LONG).show();
            return;
        }

        List<Result> results = page.getResults();
        Log.d(TAG, "onReceived: " + results);
        List<MoviesAdapter.MovieViewModel> paths = new ArrayList<>();
        for (Result r : results) {
            paths.add(new MoviesAdapter.MovieViewModel(r.getId(), r.getPosterPath()));
        }
        mAdapter.setData(paths);

    }


}
