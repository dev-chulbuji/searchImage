package com.example.ladmusiciankim.searchimage.data.repository;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.domain.repository.DaumImageNetworkRepository;
import com.example.ladmusiciankim.searchimage.entity.DaumImage;

import java.util.List;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class DaumImageRepository implements DaumImageDataSource{
    private static DaumImageRepository instance;

    public static DaumImageRepository getInstance() {
        if (instance == null) {
            instance = new DaumImageRepository();
        }
        return instance;
    }

    private DaumImageNetworkRepository daumImageNetworkRepository;

    public DaumImageRepository() {
        this.daumImageNetworkRepository = new DaumImageNetworkRepository();
    }

    @Override
    public void getImages(Context context, String query, int page, int perPage, LoadImageCallback loadImageCallback) {
        daumImageNetworkRepository.getImages(context, query, page, perPage, new LoadImageCallback() {
            @Override
            public void onImageLoaded(List<DaumImage> list) {
                if (loadImageCallback != null) {
                    loadImageCallback.onImageLoaded(list);
                }
            }
        });
    }
}
