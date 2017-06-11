package com.example.ladmusiciankim.searchimage.data.repository;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.data.repository.interfaces.ILoadSearchQueryCallback;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public interface SearchQueryDataSource {

    void addSearchQuery(String query);

    void getSearchQuery(Context context, final ILoadSearchQueryCallback loadQueriesCallback);
}
