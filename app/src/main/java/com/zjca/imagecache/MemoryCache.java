package com.zjca.imagecache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 内存缓存类
 * Created by ziye_huang on 2017/11/1.
 */

public class MemoryCache implements ImageCache
{
    private LruCache<String, Bitmap> mImageCache;

    public MemoryCache()
    {
        //初始化LRU缓存
        initImageCache();
    }

    private void initImageCache()
    {
        //计算可使用的最大内存
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //取四分之一的可用内存作为缓存
        int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize)
        {
            @Override
            protected int sizeOf(String key, Bitmap bitmap)
            {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap get(String url)
    {
        return mImageCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap)
    {
        mImageCache.put(url, bitmap);
    }
}
