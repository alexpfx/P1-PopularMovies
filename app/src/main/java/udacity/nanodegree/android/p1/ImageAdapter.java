package udacity.nanodegree.android.p1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by alexandre on 25/10/2016.
 */

public class ImageAdapter extends ArrayAdapter<ImageAdapter.Item> {

    private static final String TAG = "ImageAdapter";

    public ImageAdapter(List<Item> imagePaths, Context context) {
        super(context, 0, imagePaths);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.grid_view_item, parent, false);
        GridView gridView = (GridView) parent;
        gridView.setColumnWidth((gridView.getWidth()) / 3);
        Log.d(TAG, "getView: " + parent);

        ImageView imageView;
        imageView = (ImageView) view.findViewById(R.id.poster_image);

        String path = "http://image.tmdb.org/t/p/w185//" + getItem(position).path;
        Picasso.with(context).load(path).into(imageView);
        return view;
    }

    static class Item{
        public Item(int id, String path) {
            this.id = id;
            this.path = path;
        }

        private int id;
        private String path;

        public int getId() {
            return id;
        }

        public String getPath() {
            return path;
        }

        @Override
        public String toString() {
            return ""+id;
        }
    }

}
