package com.zjca.imagecache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * SD卡缓存类
 * Created by ziye_huang on 2017/11/1.
 */

public class DiskCache implements ImageCache
{
    private static String cacheDir = "";

    public DiskCache()
    {
        cacheDir = (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() : Environment.getDataDirectory().getAbsolutePath()) + "/cache/";
        File file = new File(cacheDir);
        if (!file.exists())
        {
            file.mkdirs();
        }
    }

    /**
     * 从缓存中获取图片
     * @param url
     * @return
     */
    @Override
    public Bitmap get(String url)
    {
        //从本地文件中获取该图片
        return BitmapFactory.decodeFile(cacheDir + url);
    }

    @Override
    public void put(String url, Bitmap bitmap)
    {
        //将Bitmap写入文件中
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }finally
        {
            CloseUtil.closeQuielty(fos);
        }
    }
}
