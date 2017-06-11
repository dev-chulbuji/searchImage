package com.example.ladmusiciankim.searchimage.presentation.ui.search;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.data.repository.SearchQueryRepository;
import com.example.ladmusiciankim.searchimage.presentation.adapter.contract.BaseAdapterContract;
import com.example.ladmusiciankim.searchimage.presentation.mvp.CommonPrenter;
import com.example.ladmusiciankim.searchimage.presentation.util.LogUtil;

import lombok.Setter;

/**
 * Created by ladmusician.kim on 2017. 6. 8..
 */

public class SearchPresenter extends CommonPrenter<SearchContract.View> implements SearchContract.Presenter {
    private static final String TAG = SearchPresenter.class.getSimpleName();

    private Context context;

    private BaseAdapterContract.View adapterView;

    @Setter
    private BaseAdapterContract.Model<String> adapterModel;

    @Setter
    private SearchQueryRepository searchQueryRepository;

    public SearchPresenter(SearchContract.View view, Context context) {
        super(view);
        this.context = context;
    }

    @Override
    public void onViewCreated() {
        loadSearchQueries();
    }

    @Override
    public void loadSearchQueries() {

        searchQueryRepository.getSearchQuery(context, (queries) -> {
            if (queries != null) {
                adapterModel.addItems(queries);
                adapterView.refresh();

                if (adapterModel.getItemCount() == 0) {
                    getView().showNoItemView();
                } else {
                    getView().hideNoItemView();
                }
            }
        });
    }

    @Override
    public void addSearchQuery(String query) {
        searchQueryRepository.addSearchQuery(query);
    }

    @Override
    public void onItemClick(int id, int position) {
        LogUtil.e(TAG, "click item :: " + position);
        getView().submitSearchQuery(adapterModel.getItem(position));
    }

    public void setAdapterView(BaseAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        adapterView.setOnClickListener((id, position) -> {
            onItemClick(id, position);
        });
    }
}
