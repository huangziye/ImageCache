package com.zjca.imagecache;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭Closeable对象工具类
 * Author: ziye_huang
 * Date: 2017/10/26
 */
public final class CloseUtil
{
    private CloseUtil()
    {
    }

    /**
     * 关闭Closeable对象
     *
     * @param closeable
     */
    public static void closeQuielty(Closeable closeable)
    {
        if (null != closeable)
        {
            try
            {
                closeable.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}