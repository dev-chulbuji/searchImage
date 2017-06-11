package com.example.ladmusiciankim.searchimage.domain.repository;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.data.repository.SearchQueryDataSource;
import com.example.ladmusiciankim.searchimage.data.repository.interfaces.ILoadSearchQueryCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ladmusician.kim on 2017. 6. 8..
 */

public class SearchQueryLocalRepository implements SearchQueryDataSource {
    private static final String TAG = SearchQueryLocalRepository.class.getSimpleName();

    private List<String> queries;

    public SearchQueryLocalRepository() {
        this.queries = new ArrayList<>();
    }

    @Override
    public void addSearchQuery(String query) {
        Collections.reverse(queries);
        for(int i = 0; i < queries.size(); i ++) {
            if (queries.get(i).equals(query)) {
                queries.remove(i);
            }
        }

        queries.add(query);
    }

    @Override
    public void getSearchQuery(Context context, ILoadSearchQueryCallback loadQueriesCallback) {
        Collections.reverse(queries);
        loadQueriesCallback.onImageLoaded(queries);
    }
}
