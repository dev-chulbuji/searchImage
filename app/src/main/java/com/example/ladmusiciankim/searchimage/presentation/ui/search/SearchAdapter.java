package com.example.ladmusiciankim.searchimage.presentation.ui.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.ladmusiciankim.searchimage.presentation.adapter.contract.BaseAdapterContract;
import com.example.ladmusiciankim.searchimage.presentation.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>
    implements BaseAdapterContract.Model<String>, BaseAdapterContract.View {

    private List<String> queries;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public SearchAdapter(Context context) {
        this.context = context;
        queries = new ArrayList<>();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchViewHolder(context, parent, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        if (holder == null) return;
        holder.bindView(position, getItem(position));
    }

    @Override
    public int getItemCount() {
        return queries.size();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void refreshRange(int start, int count) {

    }

    @Override
    public String getItem(int position) {
        return queries.get(position);
    }

    @Override
    public void add(String item) {
        this.queries.add(item);
    }

    @Override
    public void addItems(List<String> items) {
        this.queries.addAll(items);
    }

    @Override
    public void remove(int position) {
        this.queries.remove(position);
    }

    @Override
    public void clear() {
        this.queries.clear();
    }

    @Override
    public void setOnClickListener(OnItemClickListener clickListener) {
        this.onItemClickListener = clickListener;
    }
}
