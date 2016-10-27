package udacity.nanodegree.android.p1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by alexandre on 25/10/2016.
 */

public class ImageAdapter extends ArrayAdapter<String> {

    public ImageAdapter(List<String> imagePaths, Context context) {
        super(context,0, imagePaths);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.grid_view_item, parent, false);


        ImageView imageView;
        if (convertView == null){
            imageView = (ImageView) view.findViewById(R.id.poster_image);
        }else{
            imageView = (ImageView) convertView.findViewById(R.id.poster_image);;
        }
        String path = (String) getItem(position);
        Picasso.with(context).load(path).into(imageView);
        return view;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return "http://image.tmdb.org/t/p/w185//"+super.getItem(position);
    }
}
