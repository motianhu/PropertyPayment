package com.smona.app.propertypayment.image;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.smona.app.propertypayment.R;

public class ImageLoaderConfig {

    public static DisplayImageOptions getDefaultOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.payment_default_icon)
                .showImageForEmptyUri(R.drawable.payment_default_icon)
                .showImageOnFail(R.drawable.payment_default_icon)
                .cacheInMemory(true).cacheOnDisc(true).considerExifParams(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
                .build();
        return options;
    }

    public static void initImageLoader(Context context, String cacheDisc) {
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                context);
        builder.threadPoolSize(3);
        builder.threadPriority(Thread.NORM_PRIORITY);
        builder.memoryCache(new WeakMemoryCache());
        builder.memoryCacheSizePercentage(60);
        builder.denyCacheImageMultipleSizesInMemory();
        if (null != cacheDisc) {
            builder.discCache(new UnlimitedDiscCache(StorageUtils
                    .getOwnCacheDirectory(context, cacheDisc)));
        }
        builder.discCacheFileNameGenerator(new HashCodeFileNameGenerator());
        builder.defaultDisplayImageOptions(getDefaultOptions());

        ImageLoader.getInstance().init(builder.build());
        ImageLoader.getInstance().handleSlowNetwork(true);
    }
}
