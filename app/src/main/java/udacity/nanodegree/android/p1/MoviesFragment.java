package udacity.nanodegree.android.p1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import udacity.nanodegree.android.p1.domain.Page;
import udacity.nanodegree.android.p1.domain.Result;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements FetchMovies.Callback {
    private static final String TAG = "MoviesFragment";
    private GridView movieGrid;

    public MoviesFragment() {
        // Required empty public constructor
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
        movieGrid = (GridView) view.findViewById(R.id.grid_movies);

        fetchMovies(new FetchPopularMovies());
        getActivity().setTitle(getString(R.string.most_popular));

        return view;
    }

    private void fetchMovies(FetchMovies.FetchRules fetchRules) {
        new FetchMovies(fetchRules, this).execute();
    }

    @Override
    public void onReceived(String jsonData) {
        JsonToMovieConverter jsonConverter = new JsonToMovieConverter(jsonData);
        Page page = jsonConverter.generate();

        List<Result> results = page.getResults();
        List<String> paths = new ArrayList<>();
        for (Result r : results) {
            paths.add(r.getBackdropPath());
        }
        movieGrid.setAdapter(new ImageAdapter(paths, getContext()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        item.setChecked(!item.isChecked());
        switch (id) {
            case R.id.action_order_popular:
                fetchMovies(new FetchPopularMovies());
                break;
            case R.id.action_order_top:
                fetchMovies(new FetchTopMovies());
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

}
