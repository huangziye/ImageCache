package com.zjca.imagecache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.setImageCache(new DoubleCache());
        imageLoader.displayImage(this,"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2998553042,1280531746&fm=173&s=43629B459E6A0B174D356CA20300A092&w=500&h=340&img.JPEG",imageView);
    }
}
