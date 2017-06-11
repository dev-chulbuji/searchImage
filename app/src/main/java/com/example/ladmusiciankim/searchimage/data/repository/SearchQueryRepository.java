package com.example.ladmusiciankim.searchimage.data.repository;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.data.repository.interfaces.ILoadSearchQueryCallback;
import com.example.ladmusiciankim.searchimage.domain.repository.SearchQueryLocalRepository;

import java.util.List;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class SearchQueryRepository implements SearchQueryDataSource {
    private static SearchQueryRepository instance;

    public static SearchQueryRepository getInstance() {
        if (instance == null) {
            instance = new SearchQueryRepository();
        }
        return instance;
    }

    private SearchQueryLocalRepository searchQueryLocalRepository;

    public SearchQueryRepository() {
        this.searchQueryLocalRepository = new SearchQueryLocalRepository();
    }

    @Override
    public void addSearchQuery(String query) {
        searchQueryLocalRepository.addSearchQuery(query);
    }

    @Override
    public void getSearchQuery(Context context, ILoadSearchQueryCallback loadQueriesCallback) {
        searchQueryLocalRepository.getSearchQuery(context, new ILoadSearchQueryCallback() {
            @Override
            public void onImageLoaded(List<String> queries) {
                if (loadQueriesCallback != null) {
                    loadQueriesCallback.onImageLoaded(queries);
                }
            }
        });
    }
}
