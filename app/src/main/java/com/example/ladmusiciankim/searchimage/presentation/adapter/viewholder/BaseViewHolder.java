package com.example.ladmusiciankim.searchimage.presentation.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View view) {
        super(view);
    }

    public abstract void bindView(int position, M item);
}
