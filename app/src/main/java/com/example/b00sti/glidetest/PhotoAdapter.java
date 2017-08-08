package com.example.b00sti.glidetest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.b00sti.glidetest.model.Hit;

import java.util.List;

/**
 * Created by b00sti on 08.08.2017
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>{
    private static final String TAG = "PhotoAdapter";

    private List<Hit> images;
    private Context context;

    public PhotoAdapter(List<Hit> images, Context context) {
        Log.d(TAG, "PhotoAdapter: " + images.size());
        this.images = images;
        this.context = context;
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + images.size());
        holder.textView.setText(images.get(position).getPageURL());
        holder.imageView.setImageBitmap(null);
        Glide.with(context)
                .load(images.get(position).getWebformatURL())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(LinearLayout v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            textView = (TextView) v.findViewById(R.id.title);
        }

    }
}
