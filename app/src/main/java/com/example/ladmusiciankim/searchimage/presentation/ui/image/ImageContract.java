package com.example.ladmusiciankim.searchimage.presentation.ui.image;

import com.example.ladmusiciankim.searchimage.presentation.mvp.BasePresenter;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseContract;
import com.example.ladmusiciankim.searchimage.presentation.mvp.BaseView;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public interface ImageContract extends BaseContract{
    interface View extends BaseView {

        void showProgress();

        void hideProgress();

        void setLoading(boolean isLoading);
    }

    interface Presenter extends BasePresenter<View> {

        void onItemClick(int id, int position);

        void setQuery(String query);

        void loadImagesInit();

        void loadImagesMore();

        void loadImages(String query, int page, boolean isClear);

        boolean checkLastLoad();
    }
}
