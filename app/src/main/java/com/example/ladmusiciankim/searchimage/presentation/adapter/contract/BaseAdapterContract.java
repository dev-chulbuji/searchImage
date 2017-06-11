package com.example.ladmusiciankim.searchimage.presentation.adapter.contract;

import com.example.ladmusiciankim.searchimage.presentation.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public interface BaseAdapterContract {

    interface View {

        void refresh();

        void setOnClickListener(OnItemClickListener clickListener);
    }

    interface Model<M> {

        M getItem(int position);

        int getItemCount();

        void add(M item);

        void addItems(List<M> items);

        void remove(int position);

        void clear();
    }
}
