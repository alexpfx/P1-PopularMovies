package udacity.nanodegree.android.p1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import udacity.nanodegree.android.p1.domain.Page;
import udacity.nanodegree.android.p1.domain.Result;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements FetchMovies.Callback {

    private static final String TAG = "MoviesFragment";

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fetchMovies(new FetchPopularMovies());
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    private void fetchMovies(FetchMovies.FetchRules fetchRules) {
        new FetchMovies(fetchRules, this).execute();
    }

    @Override
    public void onReceived(String jsonData) {
        JsonToMovieConverter jsonConverter = new JsonToMovieConverter(jsonData);
        Page page = jsonConverter.generate();

        List<Result> results = page.getResults();
        for (Result r: results){
            Log.d(TAG, "\nonReceived: original title "+r.getOriginalTitle());
        }
    }
}
