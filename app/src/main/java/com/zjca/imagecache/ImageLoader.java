package com.zjca.imagecache;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ziye_huang on 2017/11/1.
 */

public class ImageLoader
{
    //图片缓存类，依赖于抽象，并且有一个默认的实现
    ImageCache mImageCache = new MemoryCache();
    //线程池，线程数量为CPU的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * 注入缓存实现
     *
     * @param cache
     */
    public void setImageCache(ImageCache cache)
    {
        mImageCache = cache;
    }

    /**
     * 加载图片
     *
     * @param url
     * @param imageView
     */
    public void displayImage(Activity activity, String url, ImageView imageView)
    {
        Bitmap bitmap = mImageCache.get(MD5Util.getMD5(url));
        if (null == bitmap)
        {
            //异步下载图片
            downloadImageAsync(activity, url, imageView);
        } else
        {
            imageView.setImageBitmap(bitmap);
        }
    }

    /**
     * 异步加载图片
     *
     * @param imageUrl
     * @param imageView
     */
    private void downloadImageAsync(final Activity activity, final String imageUrl, final ImageView imageView)
    {
        imageView.setTag(imageUrl);
        mExecutorService.submit(new Runnable()
        {
            @Override
            public void run()
            {
                final Bitmap bitmap = downloadImage(imageUrl);
                if (null == bitmap)
                {
                    return;
                }
                if (imageView.getTag().equals(imageUrl))
                {
                    /**
                     * 在UI线程显示图片
                     */
                    activity.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            imageView.setImageBitmap(bitmap);
                        }
                    });

                }
                //                mImageCache.put(imageUrl, bitmap);
                mImageCache.put(MD5Util.getMD5(imageUrl), bitmap);
            }
        });
    }

    /**
     * 下载图片
     *
     * @param imageUrl
     * @return
     */
    public Bitmap downloadImage(String imageUrl)
    {
        Bitmap bitmap = null;
        try
        {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }

}
