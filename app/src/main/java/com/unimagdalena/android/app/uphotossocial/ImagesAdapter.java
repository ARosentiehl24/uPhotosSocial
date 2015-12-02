package com.unimagdalena.android.app.uphotossocial;

/*
 * Created by albert on 2/12/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImagesAdapter extends BaseAdapter{

    private ArrayList<Images> images;
    private Context context;

    public ImagesAdapter(ArrayList<Images> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_image_grid, parent, false);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
            imageView.setImageResource(images.get(position).getImage());

            convertView.setTag(position);
        }

        return convertView;
    }
}
