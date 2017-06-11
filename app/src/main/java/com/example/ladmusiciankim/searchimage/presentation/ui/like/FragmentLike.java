package com.example.ladmusiciankim.searchimage.presentation.ui.like;

import android.os.Bundle;

import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.presentation.ui.image.ImagePresenter;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseFragment;
import com.example.ladmusiciankim.searchimage.presentation.ui.image.ImageContract;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class FragmentLike extends BaseFragment<ImageContract> {

    ImagePresenter presenter = null;

    public static FragmentLike newInstance() {
        Bundle args = new Bundle();
        FragmentLike fragment = new FragmentLike();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_like;
    }
}
