package com.example.ladmusiciankim.searchimage.data.mapper;

import com.example.ladmusiciankim.searchimage.data.entity.NetworkEntity;
import com.example.ladmusiciankim.searchimage.entity.Entity;

/**
 * Created by ladmusician.kim on 2017. 6. 8..
 */

public interface EntityMapper<T extends NetworkEntity, E extends Entity> {
    E fromNetworkObject(T obj);
    T toNetworkObject(E obj);
}
