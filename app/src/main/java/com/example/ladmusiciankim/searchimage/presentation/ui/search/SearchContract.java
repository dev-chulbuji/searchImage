package com.example.ladmusiciankim.searchimage.presentation.ui.search;

import com.example.ladmusiciankim.searchimage.presentation.mvp.BasePresenter;
import com.example.ladmusiciankim.searchimage.presentation.mvp.BaseView;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseContract;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public interface SearchContract extends BaseContract{
    interface View extends BaseView {

        void showNoItemView();

        void hideNoItemView();

        void submitSearchQuery(String string);
    }

    interface Presenter extends BasePresenter<View> {

        void addSearchQuery(String query);

        void onItemClick(int id, int position);

        void loadSearchQueries();
    }
}
