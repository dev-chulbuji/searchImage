package com.example.ladmusiciankim.searchimage.data.mapper;

import com.example.ladmusiciankim.searchimage.data.entity.DaumImageNetwork;
import com.example.ladmusiciankim.searchimage.entity.DaumImage;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class DaumImageEntityMapper implements EntityMapper<DaumImageNetwork, DaumImage> {

    @Override
    public DaumImage fromNetworkObject(DaumImageNetwork obj) {
        DaumImage image = new DaumImage();
        String filterStr = obj.getTitle().replaceAll("&lt;([^.]*?)&gt;", "");
        filterStr = filterStr.replaceAll("&#39;", "");
        filterStr = filterStr.replaceAll("&quot;", "");
        image.setTitle(filterStr);
        image.setThumbnail(obj.getThumbnail());
        image.setWidth(obj.getWidth());
        image.setHeight(obj.getHeight());
        image.setImage(obj.getImage());
        image.setAuthor(obj.getCpname());
        return image;
    }

    @Override
    public DaumImageNetwork toNetworkObject(DaumImage obj) {
        return null;
    }
}
