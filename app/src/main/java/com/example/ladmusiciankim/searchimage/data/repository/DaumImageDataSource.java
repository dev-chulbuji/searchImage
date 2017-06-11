package com.example.ladmusiciankim.searchimage.data.repository;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.entity.DaumImage;

import java.util.List;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public interface DaumImageDataSource {

    interface LoadImageCallback {

        void onImageLoaded(List<DaumImage> list);
    }

    void getImages(Context context, String query, int page, int perPage, final LoadImageCallback loadImageCallback);
}
