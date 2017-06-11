package com.example.ladmusiciankim.searchimage.presentation.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.presentation.adapter.viewholder.BaseViewHolder;
import com.example.ladmusiciankim.searchimage.presentation.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class SearchViewHolder extends BaseViewHolder<String> {

    private Context context;
    private OnItemClickListener onItemClickListener;

    @BindView(R.id.item_query_container) RelativeLayout queryContainer;
    @BindView(R.id.item_query) TextView query;

    public SearchViewHolder(
            Context context, ViewGroup parent,
            OnItemClickListener onItemClickListener) {

        super(LayoutInflater.from(context).inflate(R.layout.item_query, parent, false));

        this.context = context;
        this.onItemClickListener = onItemClickListener;

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView(int position, String item) {

        queryContainer.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v.getId(), position);
            }
        });

        query.setText(item);
    }
}
