package com.example.ladmusiciankim.searchimage.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ladmusician.kim on 2017. 6. 8..
 */

@Getter
@Setter
public class DaumImage extends Entity {

    private String Title;

    private String Thumbnail;

    private int Width;

    private int Height;

    private String Image;

    private String Author;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof DaumImage)) return false;

        DaumImage item = (DaumImage) obj;

        return getTitle().equals(item.getTitle()) &&
                getThumbnail().equals(item.getThumbnail()) &&
                getWidth() == item.getWidth() &&
                getHeight() == item.getHeight() &&
                getImage().equals(item.getImage()) &&
                getAuthor().equals(item.getAuthor());
    }
}
