package com.example.ladmusiciankim.searchimage.presentation.ui.image;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.example.ladmusiciankim.searchimage.entity.DaumImage;
import com.example.ladmusiciankim.searchimage.presentation.adapter.contract.BaseAdapterContract;
import com.example.ladmusiciankim.searchimage.presentation.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder>
    implements BaseAdapterContract.Model<DaumImage>, BaseAdapterContract.View {

    private List<DaumImage> images;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private RequestManager glideRequestManager = null;

    public ImageAdapter(Context context, RequestManager manager) {
        this.context = context;
        this.glideRequestManager = manager;
        images = new ArrayList<>();
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(context, parent, glideRequestManager, onItemClickListener);
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
        this.images.addAll(items);
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
