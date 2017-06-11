package com.example.ladmusiciankim.searchimage.presentation.ui.search;

import android.os.Bundle;

import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.presentation.ui.image.ImagePresenter;
import com.example.ladmusiciankim.searchimage.presentation.ui.image.ImageContract;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseFragment;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class FragmentSearch extends BaseFragment<ImageContract> {

    ImagePresenter presenter = null;

    public static FragmentSearch newInstance() {
        Bundle args = new Bundle();
        FragmentSearch fragment = new FragmentSearch();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }
}
