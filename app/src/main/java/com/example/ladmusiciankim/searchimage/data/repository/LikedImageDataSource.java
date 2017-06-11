package com.example.ladmusiciankim.searchimage.data.repository;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.data.repository.interfaces.IAddImageCallback;
import com.example.ladmusiciankim.searchimage.data.repository.interfaces.ILoadLikedImageCallback;
import com.example.ladmusiciankim.searchimage.entity.DaumImage;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public interface LikedImageDataSource {

    void addLikedImage(DaumImage item, IAddImageCallback addLikedImageCallback);

    void getLikedImages(Context context, int page, int perPage, final ILoadLikedImageCallback loadImageCallback);
}
