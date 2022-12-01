package com.example.newsapp.GiaVang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.newsapp.R;

import java.util.List;

public class ThumbnailAdapter extends ArrayAdapter<Thumbnail> {

    public ThumbnailAdapter(@NonNull Context context, int resource, @NonNull List<Thumbnail> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_thumbnail, parent,false);
        TextView tv_selected = convertView.findViewById(R.id.text_selected);

        Thumbnail thumbnail = this.getItem(position);
        if (thumbnail != null) {
            tv_selected.setText(thumbnail.getThanhpho());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thumbnail, parent,false);
        TextView tv_thumbnail = convertView.findViewById(R.id.text_thumbnail);

        Thumbnail thumbnail = this.getItem(position);
        if (thumbnail != null) {
            tv_thumbnail.setText(thumbnail.getThanhpho());
        }
        return convertView;
    }
}
