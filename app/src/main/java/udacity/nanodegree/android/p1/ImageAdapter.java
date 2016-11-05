package udacity.nanodegree.android.p1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandre on 25/10/2016.
 */

public class ImageAdapter extends ArrayAdapter<ImageAdapter.Item> {

    private static final String TAG = "ImageAdapter";

    public ImageAdapter(List<Item> imagePaths, Context context) {
        super(context, 0, imagePaths);
    }

    @BindView(R.id.poster_image)
    ImageView imageView;

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Context context = getContext();
        View view = (convertView != null) ? convertView : LayoutInflater.from(context).inflate(R.layout.grid_view_item, parent, false);
        ButterKnife.bind(this, view);

        GridView gridView = (GridView) parent;
        gridView.setColumnWidth((gridView.getWidth()) / 2);

        Item item = getItem(position);
        String path = context.getString(R.string.tmdb_image_base_path) + item.getPath();

        Picasso.with(context).load(path).placeholder(R.drawable.loading).error(R.drawable.error).into(imageView);

        return view;
    }

    static class Item {
        public Item(int id, String path) {
            this.id = id;
            this.path = path;
        }

        private final int id;
        private final String path;

        public int getId() {
            return id;
        }

        public String getPath() {
            return path;
        }

        @Override
        public String toString() {
            return "" + id;
        }
    }

}
