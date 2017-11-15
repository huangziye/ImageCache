package com.zjca.imagecache;

import android.graphics.Bitmap;

/**
 * Image缓存抽象类
 * Created by ziye_huang on 2017/11/1.
 */

public interface ImageCache
{
    /**
     * 获取bitmap
     *
     * @param url
     * @return
     */
    public Bitmap get(String url);

    /**
     * 把Bitmap放到缓存
     *
     * @param url
     * @param bitmap
     */
    public void put(String url, Bitmap bitmap);
}
