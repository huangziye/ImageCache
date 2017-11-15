package com.zjca.imagecache;

import android.graphics.Bitmap;

/**
 * 双缓存工具类，先从内存中获取，如果获取不到，再从SD卡获取
 * Created by ziye_huang on 2017/11/1.
 */

public class DoubleCache implements ImageCache
{
    ImageCache mMemoryCache = new MemoryCache();
    ImageCache mDiskCache = new DiskCache();

    /**
     * 先从内存缓存中获取图片，如果没有，再从SD卡中获取
     *
     * @param url
     * @return
     */
    @Override
    public Bitmap get(String url)
    {
        Bitmap bitmap = mMemoryCache.get(url);
        if (null == bitmap)
        {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    /**
     * 将图片缓存到内存和SD卡中
     *
     * @param url
     * @param bitmap
     */
    @Override
    public void put(String url, Bitmap bitmap)
    {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }
}
