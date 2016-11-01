package udacity.nanodegree.android.p1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import udacity.nanodegree.android.p1.domain.Result;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new DetailFragment()).commit();
        }

    }


    public static class DetailFragment extends Fragment implements FetchMovies.Callback {
        private TextView txtTitle;
        private ImageView imgPoster;
        private TextView txtVoteAvg;
        private TextView txtOverview;
        private TextView txtReleaseDate;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.detail_fragment, container, false);

            this.txtTitle = (TextView) rootView.findViewById(R.id.text_title);
            this.imgPoster = (ImageView) rootView.findViewById(R.id.image_poster);
            this.txtVoteAvg = (TextView) rootView.findViewById(R.id.text_vote_avg);
            this.txtReleaseDate = (TextView) rootView.findViewById(R.id.text_release_date);
            this.txtOverview = (TextView) rootView.findViewById(R.id.text_overview);

            String id = getActivity().getIntent().getStringExtra(Intent.EXTRA_TEXT);
            new FetchMovies(new GetMovie(id), this).execute();
            return rootView;
        }

        @Override
        public void onReceived(String data) {
            Gson gson = new Gson();
            Result result = gson.fromJson(data, Result.class);
            if (result == null) {
                Toast.makeText(getContext(), R.string.message_not_connected, Toast.LENGTH_LONG).show();
                return;
            }

            txtTitle.setText(result.getOriginalTitle());
            txtReleaseDate.setText(result.getReleaseDate());
            String path = getString(R.string.tmdb_image_base_path) + result.getPosterPath();
            Picasso.with(getContext()).load(path).into(this.imgPoster);

            Calendar calendar;
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getString(R.string.date_format), Locale.US);
                calendar = Calendar.getInstance();
                calendar.setTime(simpleDateFormat.parse(result.getReleaseDate()));

            } catch (ParseException e) {
                Log.e(TAG, "parse exception: ", e);
                throw new RuntimeException(e);
            }

            txtReleaseDate.setText(String.valueOf(calendar.get(Calendar.YEAR)));
            txtVoteAvg.setText(String.valueOf(result.getVoteAverage()));
            txtOverview.setText(String.valueOf(result.getOverview()));


        }
    }


}
