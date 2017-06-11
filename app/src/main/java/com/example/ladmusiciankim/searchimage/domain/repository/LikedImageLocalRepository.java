package com.example.ladmusiciankim.searchimage.domain.repository;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.data.repository.LikedImageDataSource;
import com.example.ladmusiciankim.searchimage.data.repository.interfaces.IAddImageCallback;
import com.example.ladmusiciankim.searchimage.data.repository.interfaces.ILoadLikedImageCallback;
import com.example.ladmusiciankim.searchimage.entity.DaumImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ladmusician.kim on 2017. 6. 8..
 */

public class LikedImageLocalRepository implements LikedImageDataSource {
    private static final String TAG = LikedImageLocalRepository.class.getSimpleName();

    private List<DaumImage> likedImages;

    public LikedImageLocalRepository() {
        this.likedImages = new ArrayList<>();
    }

    @Override
    public void addLikedImage(DaumImage item, IAddImageCallback addLikedImageCallback) {
        if (!likedImages.contains(item)) {
            likedImages.add(item);
        }
        addLikedImageCallback.onComepleAddLikedImage(item);
    }

    @Override
    public void getLikedImages(Context context, int page, int perPage, ILoadLikedImageCallback loadImageCallback) {
        int initIdx = page == 1 ? 0 : (page - 1) * perPage;
        int lastIdx = page * perPage - 1;

        if (likedImages.size() - 1 < lastIdx) {
            lastIdx = likedImages.size();
        }

        loadImageCallback.onImageLoaded(
                likedImages.subList(initIdx, lastIdx),
                (int)Math.ceil(likedImages.size()/perPage));
    }
}
