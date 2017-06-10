package com.example.ladmusiciankim.searchimage.presentation.ui.main.image;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.ladmusiciankim.searchimage.entity.DaumImage;
import com.example.ladmusiciankim.searchimage.presentation.adapter.contract.ImageAdapterContract;
import com.example.ladmusiciankim.searchimage.presentation.linstener.OnItemClickListener;

import java.util.List;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder>
    implements ImageAdapterContract.Model<DaumImage>, ImageAdapterContract.View {

    private List<DaumImage> images;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(context, parent, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        if (holder == null) return;
        holder.bindView(position, getItem(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public DaumImage getItem(int position) {
        return images.get(position);
    }

    @Override
    public void add(DaumImage item) {
        this.images.add(item);
    }

    @Override
    public void addItems(List<DaumImage> items) {
        this.images = items;
    }

    @Override
    public void remove(int position) {
        this.images.remove(position);
    }

    @Override
    public void clear() {
        this.images.clear();
    }

    @Override
    public void setOnClickListener(OnItemClickListener clickListener) {
        this.onItemClickListener = clickListener;
    }
}
