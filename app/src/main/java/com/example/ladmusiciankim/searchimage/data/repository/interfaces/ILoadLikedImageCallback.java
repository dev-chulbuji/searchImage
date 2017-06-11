package com.example.ladmusiciankim.searchimage.data.repository.interfaces;

import com.example.ladmusiciankim.searchimage.entity.DaumImage;

import java.util.List;

/**
 * Created by ladmusician.kim on 2017. 6. 11..
 */

public interface ILoadLikedImageCallback {
    void onImageLoaded(List<DaumImage> list, int lastPage);
}
