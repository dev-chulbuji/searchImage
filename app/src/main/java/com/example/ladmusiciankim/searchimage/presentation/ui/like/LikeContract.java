package com.example.ladmusiciankim.searchimage.presentation.ui.like;

import com.example.ladmusiciankim.searchimage.presentation.mvp.BasePresenter;
import com.example.ladmusiciankim.searchimage.presentation.mvp.BaseView;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseContract;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public interface LikeContract extends BaseContract{
    interface View extends BaseView {

        void showProgress();

        void hideProgress();

        void setLoading(boolean isLoading);
    }

    interface Presenter extends BasePresenter<View> {

        void loadImagesInit();

        void loadImagesMore();

        void loadImages(int page, boolean isClear);

        boolean checkLastLoad();
    }
}
