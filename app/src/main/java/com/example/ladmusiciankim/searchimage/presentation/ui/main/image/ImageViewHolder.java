package com.example.ladmusiciankim.searchimage.presentation.ui.main.image;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.entity.DaumImage;
import com.example.ladmusiciankim.searchimage.presentation.adapter.viewholder.BaseViewHolder;
import com.example.ladmusiciankim.searchimage.presentation.linstener.OnItemClickListener;

import butterknife.ButterKnife;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class ImageViewHolder extends BaseViewHolder<DaumImage> {

    private Context context;
    private OnItemClickListener onItemClickListener;

    public ImageViewHolder(Context context, ViewGroup parent, OnItemClickListener onItemClickListener) {

        super(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false));

        this.context = context;
        this.onItemClickListener = onItemClickListener;

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView(int position, DaumImage item) {

    }
}
