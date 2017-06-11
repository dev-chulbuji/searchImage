package com.example.ladmusiciankim.searchimage.data.repository;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.data.repository.interfaces.ILoadImageCallback;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public interface DaumImageDataSource {

    void getImages(Context context, String query,
                   int page, int perPage,
                   final ILoadImageCallback loadImageCallback);
}
