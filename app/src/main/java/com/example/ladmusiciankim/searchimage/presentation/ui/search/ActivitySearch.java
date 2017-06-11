package com.example.ladmusiciankim.searchimage.presentation.ui.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.data.repository.SearchQueryRepository;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ActivitySearch extends BaseActivity implements SearchContract.View {
    private static final String TAG = ActivitySearch.class.getSimpleName();

    private SearchAdapter queryAdapter = null;
    private SearchPresenter presenter = null;

    @BindView(R.id.search_txt) EditText txtSearch;
    @BindView(R.id.search_submit) TextView btnSubmit;
    @BindView(R.id.search_query_container) RecyclerView lvQueries;
    @BindView(R.id.search_no_item_view) TextView txtNoItemView;

    @OnClick({R.id.search_submit})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_submit:

                String query = txtSearch.getText().toString();

                setActivityResult(query);
                break;
            default:
                break;
        }
    }

    private void setActivityResult(String query) {
        getPresenter().addSearchQuery(query);

        Intent returnIntent = new Intent();
        returnIntent.putExtra(getString(R.string.arg_search_query), query);
        setResult(Activity.RESULT_OK, returnIntent);

        finish();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        queryAdapter = new SearchAdapter(this);
        getPresenter().setAdapterModel(queryAdapter);
        getPresenter().setAdapterView(queryAdapter);
        getPresenter().setSearchQueryRepository(SearchQueryRepository.getInstance());

        getPresenter().onViewCreated();

        lvQueries.setAdapter(queryAdapter);
        lvQueries.setHasFixedSize(true);
        lvQueries.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void submitSearchQuery(String string) {
        setActivityResult(string);
    }

    @Override
    public void showNoItemView() {
        lvQueries.setVisibility(View.GONE);
        txtNoItemView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoItemView() {
        lvQueries.setVisibility(View.VISIBLE);
        txtNoItemView.setVisibility(View.GONE);
    }

    private SearchPresenter getPresenter() {
        if (presenter == null) {
            presenter = new SearchPresenter(this, this);
        }
        return presenter;
    }
}
