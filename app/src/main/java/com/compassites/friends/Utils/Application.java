package com.compassites.friends.Utils;

import android.os.Build;
import android.os.StrictMode;

import com.compassites.friends.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


/**
 * Created by admin on 01-10-2015.
 */
public class Application extends android.app.Application {

    public static final boolean DEVELOPER_MODE = false;
    @Override
    public void onCreate() {
        super.onCreate();

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .showImageOnFail(R.drawable.place_banner)
                .imageScaleType(ImageScaleType.EXACTLY).build();


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCacheSize(20 * 1024 * 1024) // 20 Mb
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(new UnlimitedDiskCache(getCacheDir()))
                .diskCacheSize(20 * 1024 * 1024) //20 Mb
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .defaultDisplayImageOptions(defaultOptions)
                .threadPoolSize(30)
                .build();


        ImageLoader.getInstance().init(config);


        if (DEVELOPER_MODE
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll().penaltyDialog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll().penaltyDeath().build());
        }


    }
}
