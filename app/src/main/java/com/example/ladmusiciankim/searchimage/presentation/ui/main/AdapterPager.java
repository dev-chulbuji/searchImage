package com.example.ladmusiciankim.searchimage.presentation.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ladmusiciankim.searchimage.presentation.ui.BaseFragment;
import com.example.ladmusiciankim.searchimage.presentation.ui.image.FragmentImage;
import com.example.ladmusiciankim.searchimage.presentation.ui.like.FragmentLike;

import java.util.ArrayList;

public class AdapterPager extends FragmentStatePagerAdapter {
    private static final String TAG = AdapterPager.class.getSimpleName();

    ArrayList<BaseFragment> fragmentLists;

    public AdapterPager(FragmentManager fragmentManager) {
        super(fragmentManager);
        fragmentLists = new ArrayList<>();
        fragmentLists.add(FragmentImage.newInstance());
        fragmentLists.add(FragmentLike.newInstance());
        refresh();
    }

    @Override
    public int getCount() {
        return fragmentLists.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentLists.get(position);
    }

    public void addItem(BaseFragment item) {
        fragmentLists.add(item);
        refresh();
    }

    public ArrayList<BaseFragment> getFragmentLists() {
        return fragmentLists;
    }

    public void clear() {
        fragmentLists.clear();
        refresh();
    }

    public void refresh() {
        notifyDataSetChanged();
    }


}
