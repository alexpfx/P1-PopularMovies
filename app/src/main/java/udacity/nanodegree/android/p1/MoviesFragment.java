package udacity.nanodegree.android.p1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fetchMovies(new FetchPopularMovies());
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        movieGrid = (GridView) view.findViewById(R.id.grid_movies);
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
}
