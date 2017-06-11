package com.example.ladmusiciankim.searchimage.data.repository;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.data.repository.interfaces.IAddImageCallback;
import com.example.ladmusiciankim.searchimage.data.repository.interfaces.ILoadImageCallback;
import com.example.ladmusiciankim.searchimage.data.repository.interfaces.ILoadLikedImageCallback;
import com.example.ladmusiciankim.searchimage.domain.repository.DaumImageNetworkRepository;
import com.example.ladmusiciankim.searchimage.domain.repository.LikedImageLocalRepository;
import com.example.ladmusiciankim.searchimage.entity.DaumImage;

import java.util.List;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class DaumImageRepository implements DaumImageDataSource, LikedImageDataSource {
    private static DaumImageRepository instance;

    public static DaumImageRepository getInstance() {
        if (instance == null) {
            instance = new DaumImageRepository();
        }
        return instance;
    }

    private DaumImageNetworkRepository daumImageNetworkRepository;
    private LikedImageLocalRepository likedImageLocalRepository;

    public DaumImageRepository() {
        this.daumImageNetworkRepository = new DaumImageNetworkRepository();
        this.likedImageLocalRepository = new LikedImageLocalRepository();
    }

    @Override
    public void addLikedImage(DaumImage item, IAddImageCallback addLikedImageCallback) {
        likedImageLocalRepository.addLikedImage(item, new IAddImageCallback() {
            @Override
            public void onComepleAddLikedImage(DaumImage item) {
                if (addLikedImageCallback != null) {
                    addLikedImageCallback.onComepleAddLikedImage(item);
                }
            }
        });
    }

    @Override
    public void getLikedImages(Context context, int page, int perPage, ILoadLikedImageCallback loadImageCallback) {
        likedImageLocalRepository.getLikedImages(context, page, perPage, new ILoadLikedImageCallback() {
            @Override
            public void onImageLoaded(List<DaumImage> list, int lastPage) {
                if (loadImageCallback != null) {
                    loadImageCallback.onImageLoaded(list, lastPage);
                }
            }
        });
    }

    @Override
    public void getImages(Context context, String query, int page, int perPage, ILoadImageCallback loadImageCallback) {
        daumImageNetworkRepository.getImages(context, query, page, perPage, new ILoadImageCallback() {
            @Override
            public void onImageLoaded(List<DaumImage> list) {
                if (loadImageCallback != null) {
                    loadImageCallback.onImageLoaded(list);
                }
            }
        });
    }
}
