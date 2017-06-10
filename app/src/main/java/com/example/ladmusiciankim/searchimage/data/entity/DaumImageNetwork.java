package com.example.ladmusiciankim.searchimage.data.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

@Getter
@Setter
public class DaumImageNetwork extends NetworkEntity {
    @SerializedName("pubDate")
    private String pubDate;

    @SerializedName("title")
    private String title;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("cp")
    private String cp;

    @SerializedName("height")
    private String height;

    @SerializedName("link")
    private String link;

    @SerializedName("width")
    private String width;

    @SerializedName("image")
    private String image;

    @SerializedName("cpname")
    private String cpname;
}
