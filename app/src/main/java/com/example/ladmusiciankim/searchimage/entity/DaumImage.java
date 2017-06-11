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

}
